package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * rental
 */
import java.sql.Timestamp;

public class RentModel {
    private String rid;
    private String vid;
    private String cellphone;
    private String cardName;
    private String cardNo;
    private String cardExp;
    private int odometer;
    private String confNum;
    private Timestamp from;
    private Timestamp to;
    private String vtname;
    private String loc;

    public RentModel(String rid, String confNum, String cardName, String cardNo, String cardExp) {
        this.rid = rid;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.cardExp = cardExp;
        this.confNum = confNum;
    }

    public String getRid() {
        return rid;
    }

    public String getVid() {
        return vid;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardExp() {
        return cardExp;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getConfNum() {
        return confNum;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

    public String getVtname() {
        return vtname;
    }

    public void setVtname(String vtname){
        this.vtname = vtname;
    }

    public String getLoc() {
        return loc;
    }
}