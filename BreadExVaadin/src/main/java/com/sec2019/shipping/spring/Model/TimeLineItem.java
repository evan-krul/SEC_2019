package com.sec2019.shipping.spring.Model;

import java.util.List;

public class TimeLineItem {
    private int truckID;
    private float truckCapacity;
    private float truckWeight;
    private float truckFuelConsumption;
    private int tripID;
    private List<TimeLineParcelItem> parcelItems;

    public TimeLineItem(int truckID, float truckCapacity, float truckWeight, float truckFuelConsumption, int tripID) {
        this.truckID = truckID;
        this.truckCapacity = truckCapacity;
        this.truckWeight = truckWeight;
        this.truckFuelConsumption = truckFuelConsumption;
        this.tripID = tripID;
    }

    public int getTruckID() {
        return truckID;
    }

    public float getTruckCapacity() {
        return truckCapacity;
    }

    public float getTruckWeight() {
        return truckWeight;
    }

    public float getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public int getTripID() {
        return tripID;
    }

    public List<TimeLineParcelItem> getParcelItems() {
        return parcelItems;
    }

    public void setParcelItems(List<TimeLineParcelItem> parcelItems) {
        this.parcelItems = parcelItems;
    }
    public void addParcelItems(TimeLineParcelItem parcelItem) {
        this.parcelItems.add(parcelItem);
    }
}

