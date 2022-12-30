package com.example.musicplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder

abstract class PlayerServiceConnection(private val context: Context): ServiceConnection {
    protected lateinit var service: PlayerService

    fun bind() {
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(this)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        service = (binder as PlayerServiceBinder).service
    }

    override fun onServiceDisconnected(name: ComponentName?) {}
}