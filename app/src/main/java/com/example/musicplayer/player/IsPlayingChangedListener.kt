package com.example.musicplayer.player

fun interface IsPlayingChangedListener {
    fun onIsPlayingChanged(isPlaying: Boolean)
}