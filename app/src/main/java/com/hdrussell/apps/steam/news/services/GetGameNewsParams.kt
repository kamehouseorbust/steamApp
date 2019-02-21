package com.hdrussell.apps.steam.news.services

data class GetGameNewsParams (
        val appid: Double,
        val count: Int,
        val maxlength: Double,
        val format: String
)