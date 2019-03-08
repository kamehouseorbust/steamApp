package com.hdrussell.apps.steam.profile.data

data class PlayerSummary (
        val steamid: String,
        val communityvisibilitystate: Int,
        val profilestate: Int,
        val personaname: String,
        val lastlogoff: Double,
        val profileurl: String,
        val avatar: String,
        val avatarmedium: String,
        val avatarfull: String,
        val personastate: Int,
        val realname: String,
        val primaryclanid: String,
        val timecreated: Double,
        val personastateflags: Int,
        val loccountrycode: String,
        val locstatecode: String,
        val loccityid: Int
)