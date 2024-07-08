package com.example.weather_application.service;


import com.example.weather_application.model.currentweather.CurrentWeatherResponse;
import com.example.weather_application.model.daysweather.MultipleDaysWeatherResponse;
import com.example.weather_application.model.fivedayweather.FiveDayResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link CurrentWeatherResponse}
     */
    @GET("weather")
    Single<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appId
    );

    /**
     * Get five days weather forecast.
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link FiveDayResponse}
     */
    @GET("forecast")
    Single<FiveDayResponse> getFiveDaysWeather(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appId
    );

    /**
     * Get multiple days weather
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link MultipleDaysWeatherResponse}
     */
    @GET("forecast/daily")
    Single<MultipleDaysWeatherResponse> getMultipleDaysWeather(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("cnt") int dayCount,
            @Query("appid") String appId
    );

    /**
     *  Reverse geocoding
     *
     * @param lat       Latitude
     * @param lon       Longitude
     * @param limit     Number of the location names in the API response
     * @param appId String api key
     * @return instance of {@link MultipleDaysWeatherResponse}
     */
    @GET("reverse")
    Single<String> getLocationName(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("limit") int limit,
            @Query("appid") String appId
    );
}
