package com.andorid.shortsvideo.model

data class Reel(
    val reelUrl: String,
    val isFollowed: Boolean,
    val title: String, var isPlaying: Boolean = false, var isMediaReady: Boolean = false
)
