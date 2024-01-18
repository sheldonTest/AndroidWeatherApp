package com.aitronics.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aitronics.jetweatherforecast.data.CallbackResult
import com.aitronics.jetweatherforecast.model.Weather
import com.aitronics.jetweatherforecast.repository.WeatherRepository
import com.aitronics.jetweatherforecast.utils.TimePeriod
import com.aitronics.jetweatherforecast.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    val data = MutableStateFlow<CallbackResult<Weather,Boolean,Exception>>(CallbackResult(null,true,Exception("")))
    val state : StateFlow<CallbackResult<Weather,Boolean,Exception>> = data.asStateFlow()
    private val uiState = MutableStateFlow(UiState())

     fun getWeather(city: String)  {
        viewModelScope.launch {
            data.update {CallbackResult(loading = true) }
            data.update { repository.getWeather(city) }
        }
    }

    fun onValueChange(value: String) {
        Log.d("VIEWMODEL_ONVALUE",value)
       uiState.value = uiState.value.copy(textField = value)
    }


}