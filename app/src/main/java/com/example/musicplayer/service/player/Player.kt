package com.example.musicplayer.service.player

import com.example.musicplayer.db.Track

interface Player {
    val sessionId: Int
    val isPlaying: Boolean
    var track: Track?
    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun addOnPreparedListener(listener: () -> Unit)
    fun removeOnPreparedListener(listener: () -> Unit)
    fun setBalance(balance: Int)
    fun playPause()
    fun pause()
    fun playTrack(track: Track)
}