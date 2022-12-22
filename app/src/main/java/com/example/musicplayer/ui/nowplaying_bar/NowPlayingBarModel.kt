package com.example.musicplayer.ui.nowplaying_bar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import com.example.musicplayer.ui.ServiceModel

class NowPlayingBarModel(context: Context) : ServiceModel(context) {
    val cover: Bitmap? get() {
        if (service.player.track == null) {
            return null
        }

        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(service.player.track?.path)
        val data = mmr.embeddedPicture

        if (data != null) {
            return BitmapFactory.decodeByteArray(data, 0, data.size)
        } else {
            return null
        }
    }

    val nowPlayingTitle get() = service.player.track?.title
    val isPlaying get() = service.player.isPlaying
    val hasTrack get() = service.player.track != null

    fun playPause() {
        service.player.playPause()
    }
}