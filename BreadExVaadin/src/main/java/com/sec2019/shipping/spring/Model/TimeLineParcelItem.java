package com.sec2019.shipping.spring.Model;

public class TimeLineParcelItem {
    private int parcelId;
    private float parcelArive;
    private float parcelExpire;
    private float parcelWeight;

    public TimeLineParcelItem(int parcelId, float parcelArive, float parcelExpire, float parcelWeight) {
        this.parcelId = parcelId;
        this.parcelArive = parcelArive;
        this.parcelExpire = parcelExpire;
        this.parcelWeight = parcelWeight;
    }

    public int getParcelId() {
        return parcelId;
    }

    public float getParcelArive() {
        return parcelArive;
    }

    public float getParcelExpire() {
        return parcelExpire;
    }

    public float getParcelWeight() {
        return parcelWeight;
    }
}

//TODO implement more