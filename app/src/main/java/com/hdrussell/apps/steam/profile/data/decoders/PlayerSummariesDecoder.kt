package com.hdrussell.apps.steam.profile.data.decoders

import com.hdrussell.apps.steam.profile.data.PlayerSummary
import com.hdrussell.apps.steam.profile.data.PlayerSummaries
import org.json.JSONObject

class PlayerSummariesDecoder {

    fun decode(p_input: JSONObject?): PlayerSummaries {
        val playerItemsJsonArray = p_input!!.optJSONObject("response").optJSONArray("players")
        val playerItemsList = ArrayList<PlayerSummary>()
        for(i in 0..(playerItemsJsonArray.length()-1)) {
            playerItemsList.add(PlayerSummaryDecoder().decode(playerItemsJsonArray.getJSONObject(i)))
        }
        return PlayerSummaries(
                playerItemsList = playerItemsList
        )
    }
}