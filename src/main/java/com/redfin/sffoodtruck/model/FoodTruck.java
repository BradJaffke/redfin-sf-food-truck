package com.redfin.sffoodtruck.model;

/**
 * Basic model class to represent a food truck in SF
 */
public class FoodTruck {

    private String truckName;

    private String truckAddress;

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getTruckAddress() {
        return truckAddress;
    }

    public void setTruckAddress(String truckAddress) {
        this.truckAddress = truckAddress;
    }

    public FoodTruck() {}

    public FoodTruck(String truckName, String truckAddress)
    {
        this.truckName = truckName;
        this.truckAddress = truckAddress;
    }
}