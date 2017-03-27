package com.example.namjai.namjaiapp.location.bean;

/**
 * Created by namjai on 2017-03-20.
 */

public class locationBean {
    double longitude;       //위도
    double latitude;        //경도
    double altitude;        //고도
    float accuracy;         //정확도
    String provider;     //위치제공자


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
