package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.VehicleModel;
import ca.ubc.cs304.model.RentModel;
import ca.ubc.cs304.model.ReserveModel;
import ca.ubc.cs304.model.ReturnModel;
import ca.ubc.cs304.model.VehicleTypeModel;
import ca.ubc.cs304.model.CustomerModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClerkTransactions extends TerminalTransaction{

    private static String accessKey = "123456";

    public ClerkTransactions(){
    }

    public void login(String inputKey, TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;
        if (inputKey.equals(accessKey)){
            clerkMenu();
        }
    }

    public void clerkMenu(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;
        while (true){
            System.out.println("Choose one of the following options:");
            System.out.println();
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. Generate a report");
            System.out.println("4. Logout");
            System.out.print("Please choose one of the above 4 options: ");
            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        handleRent();
                        break;
                    case 2:
                        handleReturn();
                        break;
                    case 3:
                        handleReports();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }


    }

    private void handleRent() {
        int choice = INVALID_INPUT;
        while (true){
            System.out.println("Have reservation?");
            System.out.println();
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("3. Back");
            System.out.print("Please choose one of the above 3 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        rentWithReservation();
                        break;
                    case 2:
                        rentWithNoReservation();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }
    }

    private void handleReturn() {
        String plate = null;
        while (plate == null || plate.length() <= 0){
            System.out.println("License plate:");
            plate = readLine().trim();
        }
        ReturnModel rm = new ReturnModel(plate);
        delegate.return_(rm);
    }

    private void handleReports() {
        int choice = INVALID_INPUT;
        while (true){
            System.out.println("Choose report type:");
            System.out.println();
            System.out.println("1. Daily Rentals");
            System.out.println("2. Daily Rentals for Branch");
            System.out.println("3. Daily Returns");
            System.out.println("4. Daily Returns for Branch");
            System.out.print("Please choose one of the above 4 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        dailyRentalsReport();
                        return;
                    case 2:
                        dailyRentalsBranchReport();
                        return;
                    case 3:
                        dailyReturnsReport();
                        return;
                    case 4:
                        dailyReturnsBranchReport();
                        return;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        return;
                }
            }
        }
    }

    private void dailyReturnsBranchReport() {
        //TODO
    }

    private void dailyReturnsReport() {
        //TODO
    }

    private void dailyRentalsBranchReport() {
        //TODO
    }

    private void dailyRentalsReport() {
        //TODO
    }

    private void rentWithReservation() {
        String conf_num = null;
        while (conf_num == null || conf_num.length() <= 0){
            System.out.println("Reservation confirmation number:");
            conf_num = readLine().trim();
        }
        rent(conf_num);
    }

    private void rentWithNoReservation() {
        Integer confNum = (int)(Math.random() * 10000);
        String c = Integer.toString(confNum);
        rent(c);
    }

    private void rent(String conf_num){
        System.out.println("Credit card information:");

        String card_name = null;
        while (card_name == null || card_name.length() <= 0){
            System.out.print("    Name: ");
            card_name = readLine().trim();
        }

        String card_no = null;
        while (card_no == null || card_no.length() <= 0){
            System.out.print("    Number (no -'s): ");
            card_no = readLine().trim();
        }

        String exp_date = null;
        while (exp_date == null || exp_date.length() <= 0){
            System.out.print("    Expiration date (MM/YY): ");
            exp_date = readLine().trim();
        }

        Integer r = (int)(Math.random() * 10000);
        String rid = Integer.toString(r);

        RentModel rentModel = new RentModel(rid, conf_num, card_name, card_no, exp_date);
        delegate.rent(rentModel);
    }
}
