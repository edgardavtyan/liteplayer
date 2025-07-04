package com.example.musicplayer.service.player.vanilla

import android.media.MediaPlayer
import com.example.musicplayer.db.Track
import com.example.musicplayer.lib.CoverReader
import com.example.musicplayer.service.player.Player

class VanillaPlayer(private val coverReader: CoverReader) : Player {
    private val player = MediaPlayer()

    private val onIsPlayingChangedListeners = ArrayList<(Boolean) -> Unit>()
    private val onPreparedListeners = ArrayList<() -> Unit>()

    init {
        player.setOnPreparedListener { onPrepared() }
    }

    override val sessionId: Int = player.audioSessionId
    override val isPlaying: Boolean get() = player.isPlaying
    override val duration: Int get() = player.duration
    override var position: Int
        get() = player.currentPosition
        set(value) { player.seekTo(value) }
    override var track: Track? = null

    override fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.add(listener)
    }

    override fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.remove(listener)
    }

    override fun addOnPreparedListener(listener: () -> Unit) {
        onPreparedListeners.add(listener)
    }

    override fun removeOnPreparedListener(listener: () -> Unit) {
        onPreparedListeners.remove(listener)
    }

    override fun setBalance(balance: Int) {
        if (balance < 0) {
            player.setVolume(1.0f, (100 + balance) / 100.0f)
        } else if (balance > 0) {
            player.setVolume((100 - balance) / 100.0f, 1.0f)
        } else {
            player.setVolume(1.0f, 1.0f)
        }
    }

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
        track?.cover = coverReader.getCover(track!!.path)
        player.start()
        onPreparedListeners.forEach { it() }
        onIsPlayingChangedListeners.forEach { it(isPlaying) }
    }
}