package com.hdrussell.apps.steam.profile

import android.app.Activity
import android.os.Bundle
import com.hdrussell.apps.steam.PrivateKey
import com.hdrussell.apps.steam.profile.services.GetPlayerItemParams
import com.hdrussell.apps.steam.profile.services.GetPlayerItemService
import okhttp3.OkHttpClient

class TestGetPlayerItemService : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val m_client = OkHttpClient()

        val playerItemsService = GetPlayerItemService(m_client)

        playerItemsService.execute(GetPlayerItemParams(PrivateKey().key, 76561197960435530.toLong(), "json")).subscribe(
                {
                    val f = 0
                },
                {
                    val f = 0
                }
        )
    }
}