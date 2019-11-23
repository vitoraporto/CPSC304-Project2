package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class TerminalTransaction {
    static final String EXCEPTION_TAG = "[EXCEPTION]";
    static final String WARNING_TAG = "[WARNING]";
    static final int INVALID_INPUT = Integer.MIN_VALUE;
    static final int EMPTY_INPUT = 0;

    BufferedReader bufferedReader = null;
    TerminalTransactionsDelegate delegate = null;

    //should return confirmation number
    String reserve() {
        //todo
        return null;
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
