package ca.ubc.cs304.model;

import java.util.ArrayList;

public class ViewVehiclesModel {
    private String carType;
    private String location;
    private String pickUpDate;
    private String pickUpTime;
    private String returnDate;
    private String returnTime;
    private ArrayList<VehicleModel> vehicles = new ArrayList<>();


    public ViewVehiclesModel(String carType, String location, String pickUpDate, String pickUpTime, String returnDate, String returnTime) {
        this.carType = carType;
        this.location = location;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
    }

    public ArrayList<VehicleModel> getVehicles() {
        return vehicles;
    }
}
