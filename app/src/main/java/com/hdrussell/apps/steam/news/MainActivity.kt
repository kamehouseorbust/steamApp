package com.hdrussell.apps.steam.news

import android.app.Activity
import android.os.Bundle
import com.hdrussell.apps.steam.news.services.GetGameNewsParams
import com.hdrussell.apps.steam.news.services.GetGameNewsService
import okhttp3.OkHttpClient

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val m_client = OkHttpClient()

        val gameNewsService = GetGameNewsService(m_client)

        gameNewsService.execute(GetGameNewsParams((440).toDouble(), 3, (3000).toDouble(), "json")).subscribe(
                {
                    val f = 0
                },
                {
                    val f = 0
                }
        )
    }
}
