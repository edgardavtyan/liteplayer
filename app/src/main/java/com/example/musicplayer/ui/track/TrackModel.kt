package com.example.musicplayer.ui.track

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.musicplayer.db.TrackDB
import com.example.musicplayer.service.PlayerService

class TrackModel(private val context: Context, albumId: Int): ServiceConnection {
    var tracks = TrackDB(context).getTracksWithAlbumId(albumId)

    private var service: PlayerService? = null

    fun bind() {
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(this)
    }

    fun playTrack(position: Int) {
        service?.player?.playTrack(tracks[position])
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder) {
        service = (binder as PlayerService.PlayerBinder).getService()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }
}