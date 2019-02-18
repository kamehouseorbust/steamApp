package com.hdrussell.apps.steam.news.data.decoders

import com.hdrussell.apps.steam.news.data.GameNewsItem
import com.hdrussell.apps.steam.news.data.GameNewsItems
import org.json.JSONObject

class GameNewsItemsDecoder {

    fun decode(p_input: JSONObject?): GameNewsItems {
        val newsItemsJsonArray = p_input!!.optJSONObject("appnews").optJSONArray("newsitems")
        val newsItemsList = ArrayList<GameNewsItem>()
        for(i in 0..(newsItemsJsonArray.length()-1)) {
            newsItemsList.add(GameNewsItemDecoder().decode(newsItemsJsonArray.getJSONObject(i)))
        }
        return GameNewsItems(
                gamesNewsItemsList = newsItemsList
        )
    }
}