package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by klaudia on 01/08/18.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String location, long date, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = date;
        this.mUrl = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getDateString(){
        Date dateObject = new Date(mTimeInMilliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM DD, yyyy");
        String date = simpleDateFormat.format(dateObject);
        return date;
    }

    public String getTimeString(){
        Date dateObject = new Date(mTimeInMilliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm"); //"h:mm a"
        String time = simpleDateFormat.format(dateObject);
        return time;
    }

    public String getUrl() {
        return mUrl;
    }
}
