package com.aitronics.jetweatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aitronics.jetweatherforecast.model.Weather
import com.aitronics.jetweatherforecast.navigation.WeatherScreens
import com.aitronics.jetweatherforecast.utils.TimePeriod
import com.aitronics.jetweatherforecast.utils.Utils.formatDate
import com.aitronics.jetweatherforecast.utils.Utils.formatTemp
import com.aitronics.jetweatherforecast.utils.Utils.getTime
import com.aitronics.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun WeatherMainScreen(navController: NavController,city: String?,
                      viewModel: MainViewModel = hiltViewModel()) {
    val weatherData by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.getWeather(city = city!!)
    })

    Log.d("MAIN_SCREEN",city!!)

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    }

    weatherData.data?.let {
        weatherData.loading = false
        MainScaffold(it, viewModel = viewModel,navController)
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather,viewModel: MainViewModel,navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
            elevation = 10.dp, onButtonClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            })

    }, content = { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Content(data = weather)
        }
    })
}

@Composable
fun Content(data: Weather) {
 Column(
     Modifier
         .padding(4.dp)
         .fillMaxWidth(),
     verticalArrangement = Arrangement.Center,
     horizontalAlignment = Alignment.CenterHorizontally) {

     Text(
         formatDate( data.list[0].dt),
         style = MaterialTheme.typography.bodySmall,
         color = MaterialTheme.colorScheme.secondary,
         fontWeight = FontWeight.SemiBold,
         modifier = Modifier.padding(6.dp))

     Surface(modifier = Modifier
         .padding(4.dp)
         .size(200.dp),
         shape = CircleShape,
         color = Color(0xFFECC339)
     ) {
         Column(verticalArrangement = Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally) {
             var tempText = 0.0
             when(getTime()) {
                 TimePeriod.NIGHT.period -> tempText = data.list[0].feels_like.night
                 TimePeriod.EVENING.period -> tempText = data.list[0].feels_like.eve
                 TimePeriod.MORNING.period -> tempText = data.list[0].feels_like.morn
                 TimePeriod.DAY.period -> tempText = data.list[0].feels_like.day
             }

             Text(text = formatTemp(tempText) + "°",
                 style = MaterialTheme.typography.displayMedium,
                 fontWeight = FontWeight.ExtraBold)

             Text(text = data.list[0].weather[0].main, fontStyle = FontStyle.Italic)
         }

     }
 }

 }