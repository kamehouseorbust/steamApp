package com.hdrussell.apps.steam.news.data.decoders

import com.hdrussell.apps.steam.news.data.GameNewsItem
import org.json.JSONObject

class GameNewsItemDecoder {

    fun decode(p_value: JSONObject): GameNewsItem {
        return GameNewsItem(
                gid = p_value.optString("gid"),
                title = p_value.optString("title"),
                url = p_value.optString("url"),
                is_external_url = p_value.optBoolean("is_external_url"),
                author = p_value.optString("author"),
                contents = p_value.optString("contents"),
                feedlabel = p_value.optString("feedlabel"),
                date = p_value.optDouble("date"),
                feedname = p_value.optString("String"),
                feed_type = p_value.optInt("feed_type"),
                appid = p_value.optInt("appid")
        )
    }
}