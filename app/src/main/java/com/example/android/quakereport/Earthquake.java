package com.example.android.quakereport;

/**
 * Created by klaudia on 01/08/18.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private String mDate;

    public Earthquake(double magnitude, String location, String date) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mDate = date;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }
}
