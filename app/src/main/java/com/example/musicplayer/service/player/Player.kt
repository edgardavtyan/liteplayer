package com.example.musicplayer.service.player

import com.example.musicplayer.db.Track

interface Player {
    var onPreparedListener: (() -> Unit)?
    val isPlaying: Boolean
    val track: Track?
    var balance: Int
    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun playPause()
    fun pause()
    fun playTrack(track: Track)
}