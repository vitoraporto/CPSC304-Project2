package ca.ubc.cs304.model;

public class VehicleModel {
    private final String plate;
    private final String make;
    private final String model;
    private final int year;

    public VehicleModel(String plate, String make, String model, int year) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
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

    public void printInformation() {
        System.out.println("license plate: " + getPlate() + " Make: " + getMake() + " Model: " + getModel() + " Year: " + getYear());
    }
}
