package com.example.weather_application.utils;

public class Constants {
  public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
  public static final String UNITS = "metric";
  public static final String[] DAYS_OF_WEEK = {
      "Sunday",
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday"
  };
  public static final String[] MONTH_NAME = {
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December"
  };

  public static final String[] DAYS_OF_WEEK_PERSIAN = {
        "Chủ Nhật",
        "Thứ Hai",
        "Thứ Ba",
        "Thứ Tư",
        "Thứ Năm",
        "Thứ Sáu",
        "Thứ Bảy"
  };
  public static final String[] MONTH_NAME_PERSIAN = {
        "Tháng Một",
        "Tháng Hai",
        "Tháng Ba",
        "Tháng Tư",
        "Tháng Năm",
        "Tháng Sáu",
        "Tháng Bảy",
        "Tháng Tám",
        "Tháng Chín",
        "Tháng Mười",
        "Tháng Mười Một",
        "Tháng Mười Hai"
  };

  public static final String[] WEATHER_STATUS = {
      "Thunderstorm",
      "Drizzle",
      "Rain",
      "Snow",
      "Atmosphere",
      "Clear",
      "Few Clouds",
      "Broken Clouds",
      "Cloud"
  };

  public static final String[] WEATHER_STATUS_PERSIAN = {
        "Giông bão",
        "Mưa phùn",
        "Mưa",
        "Tuyết",
        "Không khí",
        "Quang đãng",
        "Ít mây",
        "Mây rải rác",
        "Mây"
  };


  public static final String CITY_INFO = "city-info";
  public static final String FAVORITE_CITIES = "fav-city";

  public static final long TIME_TO_PASS = 6 * 600000;

  public static final String LAST_STORED_CURRENT = "last-stored-current";
  public static final String LAST_STORED_MULTIPLE_DAYS = "last-stored-multiple-days";
  public static final String OPEN_WEATHER_MAP_WEBSITE = "https://home.openweathermap.org/api_keys";

  public static final String API_KEY = "0901768576d90780d7380e2c229dd0fb";
  public static final String LANGUAGE = "language";
  public static final String DARK_THEME = "dark-theme";
  public static final String FIVE_DAY_WEATHER_ITEM = "five-day-weather-item";
}
