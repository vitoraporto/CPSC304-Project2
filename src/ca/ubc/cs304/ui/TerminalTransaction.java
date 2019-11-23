package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.ReserveModel;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class TerminalTransaction {
    static final String EXCEPTION_TAG = "[EXCEPTION]";
    static final String WARNING_TAG = "[WARNING]";
    static final int INVALID_INPUT = Integer.MIN_VALUE;
    static final int EMPTY_INPUT = 0;

    BufferedReader bufferedReader = null;
    TerminalTransactionsDelegate delegate = null;

    ReserveModel reserve() {
        String location = null;
        while (location == null || location.length() <= 0){
            System.out.println("Location:");
            location = readLine().trim();
        }

        String carType = null;
        while (carType == null || carType.length() <= 0){
            System.out.println("Type of the vehicle:");
            carType = readLine().trim();
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

        return delegate.reserve(location, carType,pickUpDate,pickUpTime,returnDate,returnTime);
    }

    int readInteger(boolean allowEmpty) {
        String line = null;
        int input = INVALID_INPUT;
        try {
            line = bufferedReader.readLine();
            input = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not an integer");
            }
        }
        return input;
    }

    String readLine() {
        String result = null;
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }
}
