package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RentModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClerkTransactions extends TerminalTransaction{

    private static String accessKey = "123456";

    public ClerkTransactions(){
    }

    public void login(String inputKey, TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;
        if (inputKey.equals(accessKey)){
            System.out.println("login line 19 clerkTransactions");
            clerkMenu();
        }
    }

    public void clerkMenu(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;
        System.out.println("clerkMenu() line 26");
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
        delegate.return_(plate);
    }

    private void handleReports() {
        //todo
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
        String conf_num = reserve().getConfirmationNumber();
        rent(conf_num);
    }

    private void rent(String conf_num){
        System.out.println("Credit card information:");

        String card_name = null;
        while (card_name == null || card_name.length() <= 0){
            System.out.print("    Name:");
            card_name = readLine().trim();
        }

        String card_no = null;
        while (card_no == null || card_no.length() <= 0){
            System.out.print("    Number:");
            card_no = readLine().trim();
        }

        String exp_date = null;
        while (exp_date == null || exp_date.length() <= 0){
            System.out.print("    Expiration date:");
            exp_date = readLine().trim();
        }

        RentModel rent = delegate.rent(conf_num,card_name,card_name,exp_date);
        if (rent != null)
            rent.printRentInformations();
    }
}
