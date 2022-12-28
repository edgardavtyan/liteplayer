package com.example.musicplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.musicplayer.service.PlayerService

abstract class PlayerServiceConnection(private val context: Context): ServiceConnection {
    protected lateinit var service: PlayerService

    protected var onServiceConnectedListener: ((PlayerService) -> Unit)? = null

    fun bind() {
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(this)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        service = (binder as PlayerService.PlayerBinder).getService()
        onServiceConnectedListener?.invoke(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {}
}