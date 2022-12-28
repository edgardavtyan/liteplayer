package com.example.musicplayer.player

interface AudioEngine {
    var onPreparedListener: (() -> Unit)?
    val isPlaying: Boolean
    var balance: Int
    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun playPause()
    fun pause()
    fun playTrack(filename: String)
}