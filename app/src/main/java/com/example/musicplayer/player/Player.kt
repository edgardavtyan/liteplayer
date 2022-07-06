package com.example.musicplayer.player

import android.media.MediaPlayer
import com.example.musicplayer.db.Track

class Player {
    private val player = MediaPlayer()

    val isPlaying get() = player.isPlaying

    var track: Track? = null

    init {
        player.setOnPreparedListener { this.onPrepared() }
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
    }

    private fun onPrepared() {
        player.start()
    }
}