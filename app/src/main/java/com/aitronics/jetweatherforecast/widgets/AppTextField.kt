package com.aitronics.jetweatherforecast.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(valueState:String,
                 placeHolder: String,
                 onAction: KeyboardActions = KeyboardActions.Default,
                 keyboardType: KeyboardType = KeyboardType.Text,
                 imeAction: ImeAction = ImeAction.Next,
                 onValueChanged: (String) -> Unit) {

    OutlinedTextField(
        value = valueState,
        onValueChange = onValueChanged,
        label = {
        Text(placeHolder)
        }, maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp) )




}