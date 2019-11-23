package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RentModel;
import ca.ubc.cs304.model.ReserveModel;
import ca.ubc.cs304.model.VehicleModel;
import ca.ubc.cs304.ui.GeneralTransactions;

import java.util.ArrayList;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Bank implements LoginWindowDelegate, TerminalTransactionsDelegate {
	private DatabaseConnectionHandler dbHandler = null;

	public Bank() {
		dbHandler = new DatabaseConnectionHandler();
	}
	
	private void start() {
		login("ora_vitorap","a68475847");
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
    public RentModel rent(String conf_num, String card_name, String card_name1, String exp_date) {
        return dbHandler.rent(conf_num, card_name, card_name1, exp_date);
    }

	@Override
	public ReserveModel reserve(String location, String carType, String pickUpDate, String pickUpTime, String returnDate, String returnTime) {
		return dbHandler.reserve(location, carType, pickUpDate, pickUpTime, returnDate, returnTime);
	}

	@Override
	public int numberVehicles(String carType, String location, String pickUpDate, String pickUpTime, String returnDate, String returnTime) {
		return dbHandler.numberVehicles(carType, location, pickUpDate, pickUpTime, returnDate, returnTime);
	}

	@Override
	public ArrayList<VehicleModel> vehiclesInformation(String carType, String location, String pickUpDate, String pickUpTime, String returnDate, String returnTime) {
		return dbHandler.vehiclesInformation(carType, location, pickUpDate, pickUpTime, returnDate, returnTime);
	}

	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Bank bank = new Bank();
		bank.start();
	}
}
