package com.hdrussell.apps.steam.profile.services

data class GetPlayerItemParams (
        val key: String,
        val steamid: Double,
        val format: String
)