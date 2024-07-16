package com.example.weather_application.model.common;

import com.google.gson.annotations.SerializedName;

public class Clouds {

  @SerializedName("all")
  private int all;

  private int cloudQuantity;

  public int getAll() {
    return all;
  }

  public void setAll(int all) {
    this.all = all;
  }
}