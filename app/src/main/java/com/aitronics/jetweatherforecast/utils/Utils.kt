package com.aitronics.jetweatherforecast.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object Utils {
    fun formatDate(timestamp: Int) : String {
        val todaysDate = SimpleDateFormat("EEE, MMM d")//DateTimeFormatter.ofPattern("E M d")
        val date = Date(timestamp.toLong() * 1000)
        return todaysDate.format(date)
    }

    fun formatTemp(temp: Double) : String = "%.0f".format(temp)

    fun getTime() : Int {
        var retVal : Int = 0
        val timeOfDay = DateTimeFormatter.ofPattern("H").format(LocalDateTime.now())
        Log.d("GET_TIME",timeOfDay)

        when(timeOfDay.toInt()) {
            in 0..5 -> retVal = TimePeriod.NIGHT.period
            in 6..11 -> retVal = TimePeriod.MORNING.period
            in 12..17 -> retVal = TimePeriod.DAY.period
            in 18..20 -> retVal = TimePeriod.EVENING.period
            in 21..23 -> retVal = TimePeriod.NIGHT.period
        }

        return retVal
    }
}
