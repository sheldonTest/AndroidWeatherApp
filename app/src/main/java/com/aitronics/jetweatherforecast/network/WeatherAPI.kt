package com.aitronics.jetweatherforecast.network

import androidx.compose.ui.unit.Constraints
import com.aitronics.jetweatherforecast.model.Weather
import com.aitronics.jetweatherforecast.model.WeatherItem
import com.aitronics.jetweatherforecast.model.WeatherObject
import com.aitronics.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = Constants.API_KEY
    ) : Weather
}