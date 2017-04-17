package com.example.android.quakereport;

/**
 * Created by lesterlie on 4/16/17.
 */

public class Earthquake {

    private double mMagnitude;

    private String mLocation;

    private long mTimeInMilliseconds;

    public Earthquake(double vMagnitude, String vLocation, long timeInMilliseconds) {
        mMagnitude = vMagnitude;
        mLocation = vLocation;
        mTimeInMilliseconds = timeInMilliseconds;
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

}
