package ca.ubc.cs304.controller;

import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.GeneralTransactions;
import ca.ubc.cs304.database.DatabaseConnectionHandler;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class SuperRent implements TerminalTransactionsDelegate {
	private DatabaseConnectionHandler dbHandler = null;

	public SuperRent() {
		dbHandler = new DatabaseConnectionHandler();
	}

	private void start() {
		login("ora_vitorap", "a68475847");
	}

	/**
	 * LoginWindowDelegate Implementation
	 * 
	 * connects to Oracle database with supplied username and password
	 */
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			GeneralTransactions transaction = new GeneralTransactions();
			transaction.showMainMenu(this);
		} else {
			System.out.println("login error");
			System.exit(-1);
		}
	}

	/**
	 * TerminalTransactionsDelegate Implementation
	 * 
	 * The TerminalTransaction instance tells us that it is done with what it's
	 * doing so we are cleaning up the connection since it's no longer needed.
	 */
	public void terminalTransactionsFinished() {
		dbHandler.close();
		dbHandler = null;

		System.exit(0);
	}

	@Override
	public void rent(RentModel rentModel) {
		dbHandler.insertRental(rentModel);
	}

	@Override
	public void reserve(ReserveModel reserveModel) {
	    dbHandler.insertReservation(reserveModel);
	}

	@Override
	public void return_(ReturnModel returnModel) {
		dbHandler.insertRerturn(returnModel);
	}

	@Override
	public void vehiclesInformation(ViewVehiclesModel vvm) {
		dbHandler.vehiclesInformation(vvm);
	}

	@Override
	public void dailyRentalsReport(RentReportModel rentReportModel) {
		dbHandler.dailyRentalsReport(rentReportModel);
	}

	@Override
	public void dailyRentalsBranchReport(BranchRentReportModel branchRentReportModel) {
		dbHandler.dailyRentalsBranchReport(branchRentReportModel);
	}

	/**
	 * Main method called at launch time
	 */

	public static void main(String args[]) {
		SuperRent superRent = new SuperRent();
		superRent.start();
	}
}
