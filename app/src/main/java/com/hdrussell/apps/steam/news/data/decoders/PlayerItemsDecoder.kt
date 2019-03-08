package com.hdrussell.apps.steam.news.data.decoders

import com.hdrussell.apps.steam.news.data.PlayerItem
import com.hdrussell.apps.steam.news.data.PlayerItems
import org.json.JSONObject

class PlayerItemsDecoder {

    fun decode(p_input: JSONObject?): PlayerItems {
        val playerItemsJsonArray = p_input!!.optJSONObject("response").optJSONArray("players")
        val playerItemsList = ArrayList<PlayerItem>()
        for(i in 0..(playerItemsJsonArray.length()-1)) {
            playerItemsList.add(PlayerItemDecoder().decode(playerItemsJsonArray.getJSONObject(i)))
        }
        return PlayerItems(
                playerItemsList = playerItemsList
        )
    }
}