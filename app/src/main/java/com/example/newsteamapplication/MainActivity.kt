package com.example.newsteamapplication

import android.app.Activity
import android.os.Bundle
import okhttp3.OkHttpClient

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val m_client = OkHttpClient()

        val gameNewsService = GetGameNewsService(m_client)

        gameNewsService.execute()
    }
}
