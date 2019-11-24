
package ca.ubc.cs304.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

//IMPORT OTHER CLASSES  
import ca.ubc.cs304.model.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private Connection connection = null;

	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	// TRANSACTIONS PERFORMED BY A CUSTOMER-----------------------------------------
	// See available cars given a car frame, car type and location
	public void checkAvailability(Timestamp from, Timestamp to, String carType, String location) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT DISTINCT v.vlicense, v.make, v.model, v.year_, COUNT(*) " + " FROM vehicles v, rentals r"
							+ " WHERE r.vid = v.vlicense" + " AND (r.from_date_and_time > ? OR r.to_date_and_time < ?)"
							+ " AND v.vtname = ? AND v.loc = ?");
			ps.setTimestamp(1, to);
			ps.setTimestamp(2, from);
			ps.setString(3, carType);
			ps.setString(4, location);

			int rowCount = ps.executeUpdate();

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// INSERTS-----------------------------------------
	public void insertRerturn(ReturnModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO return_ VALUES (?,?,?,?,?)");
			ps.setString(1, model.getRid());
			ps.setTimestamp(2, model.getDateAndTime());
			ps.setInt(3, model.getOdometer());
			ps.setString(4, model.getFullTank());
			ps.setFloat(5, model.getVal());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertRental(RentModel model) {
		// NOTE: the reservation must already exist
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO rentals VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, model.getRid());
			ps.setString(2, model.getVid());
			ps.setString(3, model.getCellphone());
			ps.setInt(4, model.getOdometer());
			ps.setString(5, model.getCardName());
			ps.setString(6, model.getCardNo());
			ps.setString(7, model.getCardExp());
			ps.setTimestamp(8, model.getFrom());
			ps.setTimestamp(9, model.getTo());
			ps.setString(10, model.getConfNum());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertCustomer(CustomerModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
			ps.setString(1, model.getCellphone());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getDlicense());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertReservation(ReserveModel model) {
		// NOTE: the customer must already exist
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO reservations VALUES (?,?,?,?,?)");
			ps.setString(1, model.getConfNum());
			ps.setString(2, model.getVtname());
			ps.setString(3, model.getCellphone());
			ps.setTimestamp(4, model.getFrom());
			ps.setTimestamp(5, model.getTo());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// GENERATING REPORTS-----------------------------------------
	// Daily rentals for entire company
	public void dailyRentalsCompany() {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT r.fromDateTime AS 'Date', v.loc AS 'Location', SUM(COUNT(*)) OVER (PARTITION BY v.loc) AS 'Num rentals at branch', v.vtname AS 'Vehicle Type', COUNT (*) AS 'Total rentals'"
							+ " FROM vehicles v, rentals r" + " WHERE r.vid = v.vlicense"
							+ " GROUP BY v.loc, v.vtname, r.fromDateTime");

			int rowCount = ps.executeUpdate();

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// Daily rentals for branch
	public void dailyRentalsBranch(String location) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT r.fromDateTime AS 'Date', v.loc AS 'Location', SUM(COUNT(*)) OVER (PARTITION BY v.loc) AS 'Num rentals at branch', v.vtname AS 'Vehicle Type', COUNT (*) AS 'Total rentals'"
							+ " FROM vehicles v, rentals r" + " WHERE r.vid = v.vlicense AND v.loc = ?"
							+ " GROUP BY v.loc, v.vtname, r.fromDateTime");
			ps.setString(1, location);
			int rowCount = ps.executeUpdate();

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// Daily returns for entire company
	public void dailyReturnsCompany() {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT ret.date_and_time AS 'Date', v.loc AS 'Location', SUM(COUNT(*)) OVER (PARTITION BY v.loc) AS 'Num rentals at branch', v.vtname AS 'Vehicle Type', COUNT (*) AS 'Total rentals', SUM (ret.val) AS 'Revenue', SUM (SUM (ret.val)) OVER (PARTITION BY v.loc) AS 'Branch Revenue'"
							+ " FROM vehicles v, rentals ren, return_ ret"
							+ " WHERE ren.vid = v.vlicense AND ret.rid = ren.rid"
							+ " GROUP BY v.loc, v.vtname, ret.date_and_time");

			int rowCount = ps.executeUpdate();

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// Daily returns for branch
	public void dailyReturnsBranch(String location) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT ret.date_and_time AS 'Date', v.loc AS 'Location', SUM(COUNT(*)) OVER (PARTITION BY v.loc) AS 'Num rentals at branch', v.vtname AS 'Vehicle Type', COUNT (*) AS 'Total rentals', SUM (ret.val) AS 'Revenue', SUM (SUM (ret.val)) OVER (PARTITION BY v.loc) AS 'Branch Revenue'"
							+ " FROM vehicles v, rentals ren, return_ ret"
							+ " WHERE ren.vid = v.vlicense AND ret.rid = ren.rid AND v.loc = ?"
							+ " GROUP BY v.loc, v.vtname, ret.date_and_time");
			ps.setString(1, location);
			int rowCount = ps.executeUpdate();

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}

			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);

			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void vehiclesInformation(ViewVehiclesModel vvm) {
		//todo
	}

	public void dailyRentalsReport(RentReportModel rentReportModel) {
		//todo
	}

	public void dailyRentalsBranchReport(BranchRentReportModel branchRentReportModel) {
		//todo
	}
}