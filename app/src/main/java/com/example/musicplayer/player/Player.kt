package com.example.musicplayer.player

import android.media.MediaPlayer
import com.example.musicplayer.db.Track

class Player {
    private val player = MediaPlayer()
    private val isPlayingChangedListeners = ArrayList<IsPlayingChangedListener>()

    val isPlaying get() = player.isPlaying

    var track: Track? = null

    init {
        player.setOnPreparedListener { this.onPrepared() }
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
        }

        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }

    fun pause() {
        player.pause()
        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }

    private fun onPrepared() {
        player.start()
        isPlayingChangedListeners.forEach { it.onIsPlayingChanged(player.isPlaying) }
    }
}