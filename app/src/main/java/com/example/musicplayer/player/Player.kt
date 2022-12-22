package com.example.musicplayer.player

import android.media.MediaPlayer
import com.example.musicplayer.db.Track
import com.example.musicplayer.service.PlayerAudioManager
import com.example.musicplayer.ui.prefs.Prefs
import kotlin.math.abs

class Player(prefs: Prefs, private val audioManager: PlayerAudioManager) {
    private val player = MediaPlayer()
    private val isPlayingChangedListeners = ArrayList<IsPlayingChangedListener>()

    val isPlaying get() = player.isPlaying

    var track: Track? = null

    init {
        player.setOnPreparedListener { this.onPrepared() }
        prefs.onAudioBalanceChangeListener = {
            if (it < 0) {
                player.setVolume(1.0F, (100 - abs(it)) / 100.0F)
            } else if (it > 0) {
                player.setVolume((100 - it) / 100F, 1.0F)
            } else {
                player.setVolume(1.0F, 1.0F)
            }
        }

        audioManager.onFocusLossListener = { pause() }
    }

    fun addOnIsPlayingChangedListener(listener: IsPlayingChangedListener) {
        isPlayingChangedListeners.add(listener)
    }

    fun removeOnIsPlayingChangedListener(listener: IsPlayingChangedListener) {
        isPlayingChangedListeners.remove(listener)
    }

    fun playTrack(track: Track) {
        this.track = track
        player.stop()
        player.reset()
        player.setDataSource(track.path)
        player.isLooping = true
        player.prepareAsync()
    }

    fun playPause() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
            audioManager.requestFocus()
        }

        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }

    fun pause() {
        player.pause()
        audioManager.abandonFocus()
        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }

    private fun onPrepared() {
        player.start()
        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }
}