package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.musicplayer.player.Player

class PlayerService: Service() {
    val player = Player()
    private val binder = PlayerBinder()

    inner class PlayerBinder: Binder() {
        fun getService() = this@PlayerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}