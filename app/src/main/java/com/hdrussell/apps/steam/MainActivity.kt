package com.hdrussell.apps.steam

import android.app.Activity
import android.os.Bundle
import com.hdrussell.apps.steam.services.GetGameNewsService
import okhttp3.OkHttpClient

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val m_client = OkHttpClient()

        val gameNewsService = GetGameNewsService(m_client)

        gameNewsService.execute()
    }
}
