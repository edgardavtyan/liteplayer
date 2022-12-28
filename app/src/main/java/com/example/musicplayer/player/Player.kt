package com.example.musicplayer.player

import com.example.musicplayer.db.Track
import com.example.musicplayer.service.PlayerAudioManager
import com.example.musicplayer.ui.prefs.Prefs

class Player(
    prefs: Prefs,
    private val audioEngine: AudioEngine,
    private val audioManager: PlayerAudioManager
) {
    val isPlaying get() = audioEngine.isPlaying

    var track: Track? = null

    init {
        prefs.onAudioBalanceChangeListener = { audioEngine.balance = it }
        audioManager.onFocusLossListener = { pause() }
    }

    fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        audioEngine.addOnIsPlayingChangedListener(listener)
    }

    fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        audioEngine.removeOnIsPlayingChangedListener(listener)
    }

    fun playTrack(track: Track) {
        this.track = track
        audioEngine.playTrack(track.path)
    }

    fun playPause() {
        audioEngine.playPause()
        audioManager.setFocused(audioEngine.isPlaying)
    }

    fun pause() {
        audioManager.setFocused(false)
    }
}