package com.example.musicplayer.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.musicplayer.service.PlayerService

open class ServiceModel(private val context: Context): ServiceConnection {
    protected lateinit var service: PlayerService

    var onServiceConnected: (() -> Unit)? = null

    var isInitialized: Boolean = false
        private set

    fun bind() {
        val intent = Intent(context, PlayerService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        context.unbindService(this)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        service = (binder as PlayerService.PlayerBinder).getService()
        isInitialized = true
        onServiceConnected?.invoke()
    }

    override fun onServiceDisconnected(name: ComponentName?) {}
}