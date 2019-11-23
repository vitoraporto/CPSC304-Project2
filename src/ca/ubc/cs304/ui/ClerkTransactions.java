package ca.ubc.cs304.ui;

public class ClerkTransactions extends TerminalTransaction{

    private static String accessKey = "123456";

    public ClerkTransactions(){
    }

    public void login(String inputKey) {
        if (inputKey.equals(accessKey)){
            clerkMenu();
        }
    }

    public void clerkMenu(){
        int choice = INVALID_INPUT;
        while (true){
            System.out.println("Choose one of the following options:");
            System.out.println();
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. Generate a report");
            System.out.println("4. Logout");

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
            System.out.println("3. Logout");

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
        //todo
    }

    private void handleReports() {
        //todo
    }

    private void rentWithReservation() {
    }

    private void rentWithNoReservation() {
    }
}
