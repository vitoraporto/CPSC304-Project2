package ca.ubc.cs304.model;

public class VehicleModel {
    private final String plate;
    private final String make;
    private final String model;
    private final int year;
    private final String carType;

    public VehicleModel(String plate, String make, String model, int year, String category) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.carType = category;
    }

    public String getPlate() {
        return plate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getCarType() { return carType; }

    public void printInformation() {
        System.out.println("License plate: " + getPlate() + " Make: " + getMake() + " Model: " + getModel() + " Year: " + getYear() + " Car type: " + getCarType());
    }
}
