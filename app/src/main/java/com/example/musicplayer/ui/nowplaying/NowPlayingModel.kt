package com.example.musicplayer.ui.nowplaying

import android.content.ComponentName
import android.content.Context
import android.graphics.Bitmap
import android.os.IBinder
import com.example.musicplayer.PlayerServiceConnection
import com.example.musicplayer.ui.CoverReader

class NowPlayingModel(context: Context, private val coverReader: CoverReader)
    : PlayerServiceConnection(context) {

    var coverArt: Bitmap? = null

    val title get() = service.player.track?.title
    val info get() = service.player.track?.artistTitle
    val isPlaying get() = service.player.isPlaying

    fun playPause() {
        service.player.playPause()
    }

    fun addOnIsPlayingChangedListener(listener: (isPlaying: Boolean) -> Unit) {
        service.player.addOnIsPlayingChangedListener(listener)
    }

    fun removeOnIsPlayingChangedListener(listener: (isPlaying: Boolean) -> Unit) {
        service.player.removeOnIsPlayingChangedListener(listener)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        super.onServiceConnected(name, binder)
        if (service.player.track != null) {
            coverArt = coverReader.getCover(service.player.track!!.path)
        }

        onDataLoaded?.invoke()
    }
}