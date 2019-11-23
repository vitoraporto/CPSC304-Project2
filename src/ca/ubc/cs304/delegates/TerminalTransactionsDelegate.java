package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.RentModel;
import ca.ubc.cs304.model.ReserveModel;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	
	public void terminalTransactionsFinished();

    RentModel rent(String conf_num, String card_name, String card_name1, String exp_date);

    ReserveModel reserve(String location, String carType, String pickUpDate, String pickUpTime, String returnDate, String returnTime);

    int numberVehicles(String carType, String location, String pickUpDate, String pickUpTime, String returnDate, String returnTime);
}
