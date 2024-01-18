package com.aitronics.jetweatherforecast.repository

import android.util.Log
import com.aitronics.jetweatherforecast.data.CallbackResult
import com.aitronics.jetweatherforecast.model.Weather
import com.aitronics.jetweatherforecast.model.WeatherItem
import com.aitronics.jetweatherforecast.model.WeatherObject
import com.aitronics.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository
@Inject constructor(private val api: WeatherAPI) {
    suspend fun getWeather(city:String) : CallbackResult<Weather,Boolean,Exception>  {
        val response = try {
            api.getWeather(city)
        } catch (e: Exception) {
            Log.d("OHNOES", "Exception: ${e.message }" )
            return CallbackResult(e = e)
        }
        return CallbackResult(data = response)
    }
}