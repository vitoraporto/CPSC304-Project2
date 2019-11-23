package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.ReserveModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CustomerTransactions extends TerminalTransaction{

    CustomerTransactions(){
    }

    public void customerMenu(TerminalTransactionsDelegate delegate) {
        this.delegate = delegate;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;
        while (true){
            System.out.println("Choose one of the following options:");
            System.out.println();
            System.out.println("1. View number of available vehicles");
            System.out.println("2. Reserve a vehicle");
            System.out.println("3. Logout");
            System.out.print("Please choose one of the above 3 options: ");
            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        viewVehicles();
                        break;
                    case 2:
                        handleReserve();
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

    private void handleReserve() {
        ReserveModel reserve = reserve();
        reserve.printReserveInformations();
    }

    private void viewVehicles() {

        String carType = null;
        while (carType == null || carType.length() <= 0){
            System.out.println("Car type:");
            carType = readLine().trim();
        }

        String location = null;
        while (location == null || location.length() <= 0){
            System.out.println("Location:");
            location = readLine().trim();
        }

        String pickUpDate = null;
        while (pickUpDate == null || pickUpDate.length() <= 0){
            System.out.println("Pick up date:");
            pickUpDate = readLine().trim();
        }

        String pickUpTime = null;
        while (pickUpTime == null || pickUpTime.length() <= 0){
            System.out.println("Pick up time:");
            pickUpTime = readLine().trim();
        }

        String returnDate = null;
        while (returnDate == null || returnDate.length() <= 0){
            System.out.println("Return date:");
            returnDate = readLine().trim();
        }

        String returnTime = null;
        while (returnTime == null || returnTime.length() <= 0){
            System.out.println("Return time:");
            returnTime = readLine().trim();
        }

        System.out.println("Number of available vehicles:");
        System.out.println(delegate.numberVehicles(carType,location, pickUpDate,pickUpTime,returnDate,returnTime));
        //todo
    }
}
