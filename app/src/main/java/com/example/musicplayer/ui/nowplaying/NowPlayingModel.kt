package com.example.musicplayer.ui.nowplaying

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder
import com.example.musicplayer.ui.CoverReader

class NowPlayingModel(private val context: Context, private val coverReader: CoverReader)
    : ServiceConnection {

    private lateinit var service: PlayerService

    var coverArt: Bitmap? = null

    val title get() = service.player.track?.title
    val info get() = service.player.track?.artistTitle
    val isPlaying get() = service.player.isPlaying

    var onServiceConnectedListener: OnServiceConnectedListener? = null
    var onIsPlayingChangedListener: ((isPlaying: Boolean) -> Unit)? = null

    fun bind() {
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(this)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
        service = (binder as PlayerServiceBinder).service
        service.player.addOnIsPlayingChangedListener { onIsPlayingChangedListener?.invoke(it) }

        if (service.player.track != null) {
            coverArt = coverReader.getCover(service.player.track!!.path)
        }

        onServiceConnectedListener?.onConnected()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    fun playPause() {
        service.player.playPause()
    }
}