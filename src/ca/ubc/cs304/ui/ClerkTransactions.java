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
        int choice = 0;
        while (choice != 4){
            System.out.println("Choose one of the following options:");
            System.out.println();
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. Generate a report");
            System.out.println("4. Logout");

            //choice = readInteger(false);

            System.out.println(" ");
        }


    }
}
