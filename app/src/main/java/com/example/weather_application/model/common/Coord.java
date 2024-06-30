package com.example.weather_application.model.common;

import com.google.gson.annotations.SerializedName;

public class Coord {

  @SerializedName("lon")
  private double lon; //longtitute

  @SerializedName("lat")
  private double lat; //latitute

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }
}