package com.aitronics.jetweatherforecast.data

class CallbackResult<T,Boolean, E: Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null
)