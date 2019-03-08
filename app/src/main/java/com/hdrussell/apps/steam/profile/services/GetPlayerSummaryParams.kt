package com.hdrussell.apps.steam.profile.services

data class GetPlayerSummaryParams (
        val key: String,
        val steamid: Long,
        val format: String
)