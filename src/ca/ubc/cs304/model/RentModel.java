package ca.ubc.cs304.model;

public class RentModel {
    private final String confirmationNumber;
    private final String reservationDate;
    private final String carType;
    private final String location;
    private final String duration;

    public RentModel(String confirmationNumber, String reservationDate, String carType, String location, String duration) {
        this.confirmationNumber = confirmationNumber;
        this.reservationDate = reservationDate;
        this.carType = carType;
        this.location = location;
        this.duration = duration;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getCarType() {
        return carType;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public void printRentInformations() {
        System.out.println("Confirmation number:");
        System.out.println(getConfirmationNumber());
        System.out.println("Reservation date:");
        System.out.println(getReservationDate());
        System.out.println("Car type:");
        System.out.println(getCarType());
        System.out.println("Location:");
        System.out.println(getLocation());
        System.out.println("Duration of rental period:");
        System.out.println(getDuration());
    }
}
