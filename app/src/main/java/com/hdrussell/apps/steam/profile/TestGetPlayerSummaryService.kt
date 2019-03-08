package com.hdrussell.apps.steam.profile

import android.app.Activity
import android.os.Bundle
import com.hdrussell.apps.steam.PrivateKey
import com.hdrussell.apps.steam.profile.services.GetPlayerSummaryParams
import com.hdrussell.apps.steam.profile.services.GetPlayerSummaryService
import okhttp3.OkHttpClient

class TestGetPlayerSummaryService : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val m_client = OkHttpClient()

        val playerItemsService = GetPlayerSummaryService(m_client)

        playerItemsService.execute(GetPlayerSummaryParams(PrivateKey().key, 76561197960435530.toLong(), "json")).subscribe(
                {
                    val f = 0
                },
                {
                    val f = 0
                }
        )
    }
}