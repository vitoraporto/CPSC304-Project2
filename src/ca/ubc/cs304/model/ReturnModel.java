package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * return
 */
import java.sql.Timestamp;

public class ReturnModel {
    private String vlicense;
    private String rid;
    private Timestamp dateAndTime;
    private int odometer;
    private String fullTank;
    private float val;

    public ReturnModel(String vlicense) {
        this.vlicense = vlicense;
    }

    public String getRid() {
        return rid;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getFullTank() {
        return fullTank;
    }

    public float getVal() {
        return val;
    }

    public float calculatePrice() {
        // TODO: implement this
        return 0;
    }
}