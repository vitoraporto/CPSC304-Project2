package ca.ubc.cs304.model;

public class ReserveModel {
    private final String confirmationNumber;
    private final String location;
    private final String carType;
    private final String pickUpDate;
    private final String returnDate;


    public ReserveModel(String confirmationNumber, String location, String carType, String pickUpDate, String returnDate) {
        this.confirmationNumber = confirmationNumber;
        this.location = location;
        this.carType = carType;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getCarType() {
        return carType;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
