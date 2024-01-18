package com.aitronics.jetweatherforecast.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aitronics.jetweatherforecast.navigation.WeatherScreens
import com.aitronics.jetweatherforecast.screens.main.MainViewModel
import com.aitronics.jetweatherforecast.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherSearch(navController: NavController,viewModel: MainViewModel) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMain = false,
            onAddAction = {
                navController.popBackStack()
            })
    }, content = {
        Surface(modifier = Modifier.padding(it)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                SearchBar(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    onSearch = { search ->
                               Log.d("SEARCH_BAR_TEST", search)
                        navController.navigate(WeatherScreens.MainScreen.name + "/$search")
                    }, viewModel = viewModel)
            }

        }
    })
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier,onSearch: (String) -> Unit = {},viewModel: MainViewModel) {

    var queryState by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValid = remember(queryState) { queryState.trim().isNotEmpty() }

    Column {
        OutlinedTextField(
            value = queryState,
            onValueChange = {
                queryState = it
            },
            label = {
                Text("City")
            }, maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions {
                if(!isValid) {
                    return@KeyboardActions
                }
                onSearch(queryState.trim())
                queryState = ""
                keyboardController?.hide()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                cursorColor = Color.Black),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp) )
    }

}