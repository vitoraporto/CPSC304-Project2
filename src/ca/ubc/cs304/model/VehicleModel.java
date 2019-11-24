package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * vehicle
 */
public class VehicleModel {
    private final String vlicense;
    private final String status;
    private final String make;
    private final String model;
    private final int year;
    private final String color;
    private final int odometer;
    private final String vtnmae;
    private final String loc;
    private final String city;

    public VehicleModel(String vlicense, String status, String make, String model, int year, String color, int odometer,
            String vtnmae, String loc, String city) {
        this.vlicense = vlicense;
        this.status = status;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.vtnmae = vtnmae;
        this.loc = loc;
        this.city = city;
    }

    public String getVlicense() {
        return vlicense;
    }

    public String getStatus() {
        return status;
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

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getVtnmae() {
        return vtnmae;
    }

    public String getLoc() {
        return loc;
    }

    public String getCity() {
        return city;
    }
}
