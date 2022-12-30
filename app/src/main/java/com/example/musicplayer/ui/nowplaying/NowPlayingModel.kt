package com.example.musicplayer.ui.nowplaying

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.IBinder
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder

class NowPlayingModel(private val context: Context)
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
            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(service.player.track?.path)
            val data = mmr.embeddedPicture

            if (data != null) {
                coverArt = BitmapFactory.decodeByteArray(data, 0, data.size)
            }
        }

        onServiceConnectedListener?.onConnected()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    fun playPause() {
        service.player.playPause()
    }
}