package com.example.musicplayer.ui.nowplaying_bar

import android.content.Context
import android.graphics.Bitmap
import com.example.musicplayer.lib.CoverReader
import com.example.musicplayer.lib.PlayerServiceConnection

class NowPlayingBarModel(context: Context, private val coverReader: CoverReader)
    : PlayerServiceConnection(context)
{
    val cover: Bitmap? get() {
        if (service.player.track == null) {
            return null
        }

        return coverReader.getCover(service.player.track!!.path)
    }

    val nowPlayingTitle get() = service.player.track?.title
    val isPlaying get() = service.player.isPlaying
    val hasTrack get() = service.player.track != null

    fun playPause() {
        service.player.playPause()
    }
}