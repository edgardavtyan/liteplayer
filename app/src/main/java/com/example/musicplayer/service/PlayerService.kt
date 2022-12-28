package com.example.musicplayer.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
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
    companion object {
        const val ACTION_AUDIO_NOISY = "com.example.muiscplayer.ACTION_AUDIO_NOISY"
    }

    inner class AudioNoisyReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            player.pause()
        }
    }

    @Inject lateinit var player: Player
    @Inject lateinit var notification: PlayerNotification

    private val binder = PlayerBinder()
    private val audioNoisyReceiver = AudioNoisyReceiver()

    inner class PlayerBinder: Binder() {
        fun getService() = this@PlayerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        DaggerPlayerServiceComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .playerModule(PlayerModule())
            .playerServiceModule(PlayerServiceModule(this))
            .build()
            .inject(this)

        startForeground(101010, notification.notification)
        registerReceiver(audioNoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioNoisyReceiver)
    }
}