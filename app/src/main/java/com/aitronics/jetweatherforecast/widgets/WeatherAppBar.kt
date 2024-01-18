package com.aitronics.jetweatherforecast.widgets

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMain: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddAction: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    TopAppBar(title = {
        Text(text = title,
            color = MaterialTheme.colorScheme.secondary,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
        )
    }, colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent),
        actions = {
            if(isMain) {
                IconButton(onClick = {
                    onAddAction.invoke()
                }) {
                Icon(imageVector = Icons.Default.Search,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                }, contentDescription = "Search Bar" )
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Options" )
                }
            }
            else {
                Box {

                }
            }
        }, navigationIcon = {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        onAddAction.invoke()
                    })
            }
        })

}