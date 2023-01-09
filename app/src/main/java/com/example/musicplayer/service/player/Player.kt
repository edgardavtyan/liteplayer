package com.example.musicplayer.service.player

import com.example.musicplayer.db.Track

interface Player {
    val sessionId: Int
    var track: Track?
    val isPlaying: Boolean
    var seek: Int
    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun addOnPreparedListener(listener: () -> Unit)
    fun removeOnPreparedListener(listener: () -> Unit)
    fun setBalance(balance: Int)
    fun playPause()
    fun pause()
    fun playTrack(track: Track)
}