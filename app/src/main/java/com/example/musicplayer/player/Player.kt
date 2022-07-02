package com.example.musicplayer.player

import android.media.MediaPlayer
import com.example.musicplayer.db.Track

class Player {
    private val player = MediaPlayer()

    val isPlaying get() = player.isPlaying

    var track: Track? = null

    init {
        player.setOnPreparedListener { this.onPrepared() }
        player.setOnCompletionListener { this.onCompleted() }
    }

    fun playTrack(track: Track) {
        this.track = track
        player.reset()
        player.setDataSource(track.path)
        player.prepareAsync()
    }

    private fun onPrepared() {
        player.start()
    }

    private fun onCompleted() {
        player.seekTo(0)
    }

    fun playPause() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
        }
    }
}