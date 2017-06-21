package com.example.android.quakereport;

/**
 * Created by Prakhar on 16-Jun-17.
 */

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    public Earthquake(Double magnitude, String location, long date, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public Double getMagnitude(){
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public long getDate(){
        return mDate;
    }
    public String getUrl() {
        return mUrl;
    }
}
