package com.hdrussell.apps.steam.news.data

data class GameNewsItem (
        val gid: String,
        val title: String,
        val url: String,
        val is_external_url: Boolean,
        val author: String,
        val contents: String,
        val feedlabel: String,
        val date: Double,
        val feedname: String,
        val feed_type: Int,
        val appid: Int
)