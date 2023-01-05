package com.example.musicplayer.ui.nowplaying_bar

import android.content.Context
import com.example.musicplayer.lib.PlayerServiceConnection

class NowPlayingBarModel(context: Context)
    : PlayerServiceConnection(context)
{
    val cover get() = service.player.track?.cover
    val nowPlayingTitle get() = service.player.track?.title
    val isPlaying get() = service.player.isPlaying
    val hasTrack get() = service.player.track != null

    fun playPause() {
        service.player.playPause()
    }

    override fun onServiceConnected() {
        onDataLoaded?.invoke()
    }
}