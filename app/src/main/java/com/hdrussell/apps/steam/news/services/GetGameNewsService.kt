package com.hdrussell.apps.steam.news.services

import com.hdrussell.apps.steam.news.data.decoders.GameNewsItemsDecoder
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import org.json.JSONTokener

class GetGameNewsService(protected var m_client: OkHttpClient) {

    var m_url:String = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=440&count=3&maxlength=3000&format=json"

    fun execute() {
                val request = Request.Builder()
                        .url(m_url)
                        .build()

                val call = this.m_client.newCall(request)

                this.m_client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e:IOException) {}
                    override fun onResponse(call: Call, response: Response) {
                        val test = GameNewsItemsDecoder().decode(JSONTokener(response.body()!!.string()).nextValue() as JSONObject)
                        println(test.toString())
                    }
                })
        }
}