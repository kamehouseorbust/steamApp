package com.hdrussell.apps.steam.profile.data.decoders

import com.hdrussell.apps.steam.profile.data.PlayerItem
import org.json.JSONObject

class PlayerItemDecoder {

    fun decode(p_value: JSONObject): PlayerItem {
        return PlayerItem(
                steamid = p_value.optString("steamid"),
                communityvisibilitystate = p_value.optInt("communityvisibilitystate"),
                profilestate = p_value.optInt("profilestate"),
                personaname = p_value.optString("personaname"),
                lastlogoff = p_value.optDouble("lastlogoff"),
                profileurl = p_value.optString("profileurl"),
                avatar = p_value.optString("avatar"),
                avatarmedium = p_value.optString("avatarmedium"),
                avatarfull = p_value.optString("avatarfull"),
                personastate = p_value.optInt("personastate"),
                realname = p_value.optString("realname"),
                primaryclanid = p_value.optString("primaryclanid"),
                timecreated = p_value.optDouble("timecreated"),
                personastateflags = p_value.optInt("personastateflags"),
                loccountrycode = p_value.optString("loccountrycode"),
                locstatecode = p_value.optString("locstatecode"),
                loccityid = p_value.optInt("loccityid")
        )
    }
}