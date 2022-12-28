package com.example.musicplayer.service.player

import android.media.MediaPlayer
import com.example.musicplayer.db.Track

class StandardPlayer(private val player: MediaPlayer): Player {
    private val onIsPlayingChangedListeners = ArrayList<(Boolean) -> Unit>()

    init {
        player.setOnPreparedListener { onPrepared() }
    }

    override var onPreparedListener: (() -> Unit)? = null

    override var balance: Int = 0
        set(value) {
            field = value
            if (value < 0) {
                player.setVolume(1.0f, (100 + value) / 100.0f)
            } else if (value > 0) {
                player.setVolume((100 - value) / 100.0f, 1.0f)
            } else {
                player.setVolume(1.0f, 1.0f)
            }
        }

    override fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.add(listener)
    }

    override fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.remove(listener)
    }

    override val isPlaying: Boolean get() = player.isPlaying
    override var track: Track? = null

    override fun playPause() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
        }

        onIsPlayingChangedListeners.forEach { it(isPlaying) }
    }

    override fun pause() {
        player.pause()
        onIsPlayingChangedListeners.forEach { it(isPlaying) }
    }

    override fun playTrack(track: Track) {
        this.track = track
        player.stop()
        player.reset()
        player.isLooping = true
        player.setDataSource(track.path)
        player.prepareAsync()
    }

    private fun onPrepared() {
        player.start()
        onPreparedListener?.invoke()
        onIsPlayingChangedListeners.forEach { it(isPlaying) }
    }
}