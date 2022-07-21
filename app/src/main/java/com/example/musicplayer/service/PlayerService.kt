package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Binder
import android.os.IBinder
import com.example.musicplayer.App
import com.example.musicplayer.player.Player
import com.example.musicplayer.player.PlayerModule
import javax.inject.Inject

class PlayerService: Service() {

    @Inject lateinit var player: Player
    @Inject lateinit var audioNoisyReceiver: AudioNoisyReceiver

    private val binder = PlayerBinder()

    inner class PlayerBinder: Binder() {
        fun getService() = this@PlayerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()

        DaggerPlayerServiceComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .playerModule(PlayerModule())
            .playerServiceModule(PlayerServiceModule())
            .build()
            .inject(this)

        registerReceiver(audioNoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
    }
}