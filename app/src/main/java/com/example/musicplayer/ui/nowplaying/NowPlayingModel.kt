package com.example.musicplayer.ui.nowplaying

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.musicplayer.service.PlayerService

class NowPlayingModel(private val context: Context)
    : ServiceConnection {

    private lateinit var service: PlayerService

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
        service = (binder as PlayerService.PlayerBinder).getService()
        onServiceConnectedListener?.onConnected()
        service.player.addOnIsPlayingChangedListener { onIsPlayingChangedListener?.invoke(it) }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    fun playPause() {
        service.player.playPause()
    }
}