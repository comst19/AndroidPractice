package com.comst19.weatherforecastapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("1360000/VilageFcstInfoService_2.0/getVilageFcst?pageNo=1&numOfRows=400&dataType=JSON")
    fun getVillageForecast(
        @Query("serviceKey") serviceKey : String,

        @Query("base_date") baseDate : String,
        // base_date=20230327
        @Query("base_time") baseTime : String,
        // base_time=0200
        @Query("nx") nx : Int,
        @Query("ny") ny : Int
    ) : Call<WeatherEntity>
}