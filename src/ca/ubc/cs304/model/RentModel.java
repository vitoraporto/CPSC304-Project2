package ca.ubc.cs304.model;

public class RentModel {
    private final String address;
    private final String city;
    private final int id;
    private final String name;
    private final int phoneNumber;

    public RentModel(String address, String city, int id, String name, int phoneNumber) {
        this.address = address;
        this.city = city;
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
