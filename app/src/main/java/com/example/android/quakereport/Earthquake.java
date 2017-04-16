package com.example.android.quakereport;

/**
 * Created by lesterlie on 4/16/17.
 */

public class Earthquake {

    private String mEarthquakeMagnitude;

    private String mEarthquakeLocation;

    private String mEarthquakeDate;

    public Earthquake(String vEarthquakeMagnitude, String vEarthquakeLocation, String vEarthquakeDate) {
        mEarthquakeMagnitude = vEarthquakeMagnitude;
        mEarthquakeLocation = vEarthquakeLocation;
        mEarthquakeDate = vEarthquakeDate;
    }

    public String getEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    public String getEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    public String getmEarthquakeDate() {
        return mEarthquakeDate;
    }

}
