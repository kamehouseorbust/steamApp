package com.hdrussell.apps.steam.profile.services

data class GetPlayerItemParams (
        val key: String,
        val steamid: Long,
        val format: String
)