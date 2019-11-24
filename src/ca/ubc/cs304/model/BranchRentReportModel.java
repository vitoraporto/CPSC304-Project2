package ca.ubc.cs304.model;

import java.util.ArrayList;
import java.util.HashMap;

public class BranchRentReportModel {
    private ArrayList<VehicleModel> vehicles = new ArrayList<>();
    private HashMap<String, Integer> vehiclesPerCategory = new HashMap<>();
    private final String branch;
    private int totalRentals = 0;


    public BranchRentReportModel(String branch) {
        this.branch = branch;
    }

    public ArrayList<VehicleModel> getVehicles() {
        return vehicles;
    }

    public HashMap<String, Integer> getVehiclesPerCategory() {
        return vehiclesPerCategory;
    }

    public String getBranch() {
        return branch;
    }

    public int getTotalRentals() {
        return totalRentals;
    }
}
