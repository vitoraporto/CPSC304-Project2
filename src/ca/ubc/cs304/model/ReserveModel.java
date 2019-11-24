package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * reservation
 */
import java.sql.Timestamp;

public class ReserveModel {
    private final String confNum;
    private final String vtname;
    private final String cellphone;
    private final Timestamp from;
    private final Timestamp to;
    private final String location;

    public ReserveModel(String confNum, String vtname, String cellphone, Timestamp from, Timestamp to, String location) {
        this.confNum = confNum;
        this.vtname = vtname;
        this.cellphone = cellphone;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    public String getConfNum() {
        return confNum;
    }

    public String getVtname() {
        return vtname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

    public String getLocation() {
        return location;
    }
}