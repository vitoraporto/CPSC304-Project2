package ca.ubc.cs304.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RentReportModel {
    private ArrayList<VehicleModel> vehicles = new ArrayList<>();
    private HashMap<String, Integer> vehiclesPerCategory = new HashMap<>();
    private HashMap<String, Integer> rentalsPerBranch = new HashMap<>();
    private int totalRentals = 0;


    public RentReportModel() {
    }


    public ArrayList<VehicleModel> getVehicles() {
        return vehicles;
    }

    public HashMap<String, Integer> getVehiclesPerCategory() {
        return vehiclesPerCategory;
    }

    public HashMap<String, Integer> getRentalsPerBranch() {
        return rentalsPerBranch;
    }

    public int getTotalRentals() {
        return totalRentals;
    }
}
