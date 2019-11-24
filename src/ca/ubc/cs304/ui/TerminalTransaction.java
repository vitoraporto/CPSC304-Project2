package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.ReserveModel;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class TerminalTransaction {
    static final String EXCEPTION_TAG = "[EXCEPTION]";
    static final String WARNING_TAG = "[WARNING]";
    static final int INVALID_INPUT = Integer.MIN_VALUE;
    static final int EMPTY_INPUT = 0;

    BufferedReader bufferedReader = null;
    TerminalTransactionsDelegate delegate = null;

    void reserve() {
        String location = null;
        while (location == null || location.length() <= 0){
            System.out.println("Location: ");
            location = readLine().trim();
        }

        String cellphone = null;
        while (cellphone == null || cellphone.length() <= 0){
            System.out.println("Cell phone (##########): ");
            cellphone = readLine().trim();
        }

        String carType = null;
        while (carType == null || carType.length() <= 0){
            System.out.println("Type of the vehicle: ");
            carType = readLine().trim();
        }

        String pickUpDate = null;
        while (pickUpDate == null || pickUpDate.length() <= 0){
            System.out.println("Pick up date (DD/MM/YYYY): ");
            pickUpDate = readLine().trim();
        }

        String pickUpTime = null;
        while (pickUpTime == null || pickUpTime.length() <= 0){
            System.out.println("Pick up time (HH:MM): ");
            pickUpTime = readLine().trim();
        }

        String returnDate = null;
        while (returnDate == null || returnDate.length() <= 0){
            System.out.println("Return date (DD/MM/YYYY): ");
            returnDate = readLine().trim();
        }

        String returnTime = null;
        while (returnTime == null || returnTime.length() <= 0){
            System.out.println("Return time (HH:MM): ");
            returnTime = readLine().trim();
        }
        Timestamp from = createTimestamp(pickUpDate, pickUpTime);
        Timestamp to = createTimestamp(returnDate, returnTime);

        Integer confNum = (int)(Math.random() * 10000);
        String c = Integer.toString(confNum);

        ReserveModel reserveModel = new ReserveModel(c, carType, cellphone, from, to, location);
        delegate.reserve(reserveModel);

        System.out.println("Confirmation number:");
        System.out.println(reserveModel.getConfNum());
        System.out.println("Location:");
        System.out.println(reserveModel.getLocation());
        System.out.println("Car type:");
        System.out.println(reserveModel.getVtname());
        System.out.println("Pickup Date:");
        System.out.println(reserveModel.getFrom());
        System.out.println("Return date:");
        System.out.println(reserveModel.getTo());
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

    public static Timestamp createTimestamp (String date, String time) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp timestamp = null;
        try {
            java.util.Date d = dateFormat.parse(date);
            long t = d.getTime();
            timestamp = new Timestamp(t);
            String hours = time.substring(0,2);
            int hours_ = Integer.parseInt(hours);
            timestamp.setHours(hours_);
            String minutes = time.substring(3,5);
            int minutes_ = Integer.parseInt(minutes);
            timestamp.setMinutes(minutes_);
            } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
