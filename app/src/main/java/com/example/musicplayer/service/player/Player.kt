package com.example.musicplayer.service.player

import com.example.musicplayer.db.Track

interface Player {
    var onPreparedListener: (() -> Unit)?
    val sessionId: Int
    val isPlaying: Boolean
    val track: Track?
    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit)
    fun setBalance(balance: Int)
    fun playPause()
    fun pause()
    fun playTrack(track: Track)
}