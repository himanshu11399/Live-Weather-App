package com.example.weatherapp

import android.text.BoringLayout
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface Apinterface {

    @GET("weather")
    fun GetProductData(
       @Query("q") q:String,
       @Query("appid")appid:String,
//       @Query("units") metric:String
    ) : Call<WeatherApp>
}