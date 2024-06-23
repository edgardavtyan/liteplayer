package com.example.musicplayer.ui.nowplaying

import android.content.Context
import com.example.musicplayer.lib.PlayerServiceConnection

class NowPlayingModel(context: Context): PlayerServiceConnection(context) {
    val title get() = service.player.track?.title
    val info get() = service.player.track?.artistTitle
    val cover get() = service.player.track?.cover
    val isPlaying get() = service.player.isPlaying
    val duration get() = service.player.duration
    var position
        get() = service.player.position
        set(value) { service.player.position = value }

    fun playPause() {
        service.player.playPause()
    }

    fun addOnIsPlayingChangedListener(listener: (isPlaying: Boolean) -> Unit) {
        service.player.addOnIsPlayingChangedListener(listener)
    }

    fun removeOnIsPlayingChangedListener(listener: (isPlaying: Boolean) -> Unit) {
        service.player.removeOnIsPlayingChangedListener(listener)
    }

    override fun onServiceConnected() {
        onDataLoaded?.invoke()
    }
}