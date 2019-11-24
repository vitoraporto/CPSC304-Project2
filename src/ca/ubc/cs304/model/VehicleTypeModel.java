package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * vehicle type
 */
public class VehicleTypeModel {
    private final String vtnmae;
    private final String featues;
    private final float wrate;
    private final float drate;
    private final float hrate;
    private final float wirate;
    private final float dirate;
    private final float hirate;
    private final float krate;

    public VehicleTypeModel(String vtnmae, String featues, float wrate, float drate, float hrate, float wirate, float dirate, float hirate, float krate) {
        this.vtnmae = vtnmae;
        this.featues = featues;
        this.wrate = wrate;
        this.drate = drate;
        this.hrate = hrate;
        this.wirate = wirate;
        this.dirate = dirate;
        this.hirate = hirate;
        this.krate = krate;
    }

    public String getVtnmae() {
        return vtnmae;
    }

    public String getFeatues() {
        return featues;
    }

    public float getWrate() {
        return wrate;
    }

    public float getDrate() {
        return drate;
    }

    public float getHrate() {
        return hrate;
    }

    public float getWirate() {
        return wirate;
    }

    public float getDirate() {
        return dirate;
    }

    public float getHirate() {
        return hirate;
    }

    public float getKrate() {
        return krate;
    }
}
