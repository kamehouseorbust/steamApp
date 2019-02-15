package com.hdrussell.apps.steam.services

import okhttp3.*
import java.io.IOException

class GetGameNewsService(protected var m_client: OkHttpClient) {

    var m_url:String = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=440&count=3&maxlength=3000&format=json"

    fun execute() {
        var request = Request.Builder()
                .url(m_url)
                .build()

        this.m_client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e:IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }

}