package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case SuperRent).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * SuperRent is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	
    void terminalTransactionsFinished();

    void rent(RentModel rentModel);

    void reserve(ReserveModel reserveModel);

    void return_(ReturnModel returnModel);

    void vehiclesInformation(ViewVehiclesModel vvm);

    void dailyRentalsReport(RentReportModel rentReportModel);

    void dailyRentalsBranchReport(BranchRentReportModel branchRentReportModel);
}
