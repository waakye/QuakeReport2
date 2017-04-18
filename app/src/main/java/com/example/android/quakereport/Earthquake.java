package com.example.android.quakereport;

/**
 * Created by lesterlie on 4/16/17.
 */

public class Earthquake {

    private double mMagnitude;

    private String mLocation;

    private long mTimeInMilliseconds;

    private String mUrl;

    public Earthquake(double vMagnitude, String vLocation, long timeInMilliseconds, String vUrl) {
        mMagnitude = vMagnitude;
        mLocation = vLocation;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = vUrl;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }


}
