package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

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
        String branch = null;
        while (branch == null || branch.length() <= 0){
            System.out.println("Select branch:");
            branch = readLine().trim();
        }
        BranchRentReportModel branchRentReportModel = new BranchRentReportModel(branch);
        delegate.dailyRentalsBranchReport(branchRentReportModel);

        System.out.println("Daily Rentals:");
        for (int i = 0; i < branchRentReportModel.getVehicles().size(); i++) {
            VehicleModel vehicle = branchRentReportModel.getVehicles().get(i);
            System.out.println("License plate: " + vehicle.getVlicense() + " Make: " + vehicle.getMake() + " Model: " + vehicle.getModel() + " Year: " + vehicle.getYear() + " Car type: " + vehicle.getVtnmae() + " Location: " + vehicle.getLoc());
        }

        System.out.println("Number of vehicles rented per category:");
        Set<String> categories = branchRentReportModel.getVehiclesPerCategory().keySet();
        for (String category:categories) {
            System.out.println(category + ": " + branchRentReportModel.getVehiclesPerCategory().remove(category));
        }


        System.out.println("Total number of new rentals: " + branchRentReportModel.getTotalRentals());
    }

    private void dailyRentalsReport() {
        RentReportModel rentReportModel = new RentReportModel();
        delegate.dailyRentalsReport(rentReportModel);
        System.out.println("Daily Rentals:");
        for (int i = 0; i < rentReportModel.getVehicles().size(); i++) {
            VehicleModel vehicle = rentReportModel.getVehicles().get(i);
            System.out.println("License plate: " + vehicle.getVlicense() + " Make: " + vehicle.getMake() + " Model: " + vehicle.getModel() + " Year: " + vehicle.getYear() + " Car type: " + vehicle.getVtnmae() + " Location: " + vehicle.getLoc());
        }

        System.out.println("Number of vehicles rented per category:");
        Set<String> categories = rentReportModel.getVehiclesPerCategory().keySet();
        for (String category:categories) {
            System.out.println(category + ": " + rentReportModel.getVehiclesPerCategory().remove(category));
        }

        System.out.println("Number of rentals at each branch:");
        Set<String> branches = rentReportModel.getRentalsPerBranch().keySet();
        for (String branch:branches) {
            System.out.println(branch + ": " + rentReportModel.getRentalsPerBranch().remove(branch));
        }


        System.out.println("Total number of new rentals: " + rentReportModel.getTotalRentals());
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
        String conf_num = reserve();
        rent(conf_num);
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

        System.out.println("Confirmation number:");
        System.out.println(rentModel.getConfNum());
        System.out.println("Reservation date:");
        System.out.println(rentModel.getFrom());
        System.out.println("Car type:");
        System.out.println(rentModel.getVtname());
        System.out.println("Location:");
        System.out.println(rentModel.getLoc());
        System.out.println("Duration of rental period:");
        System.out.println("From " + rentModel.getFrom() + " to " + rentModel.getTo());
    }
}
