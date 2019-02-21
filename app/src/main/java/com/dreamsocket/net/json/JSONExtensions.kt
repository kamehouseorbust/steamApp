package com.dreamsocket.net.json

import android.text.TextUtils
import org.json.JSONObject

fun JSONObject.optNullableString(p_name:String):String?{
    val value:String? = this.optString(p_name)

    return if (TextUtils.isEmpty(value)) null else value
}