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
import com.example.musicplayer.service.player.Player
import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.ui.prefs.Prefs
import javax.inject.Inject


class PlayerService: Service() {
    inner class AudioNoisyReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            player.pause()
        }
    }

    inner class PlayerBinder: Binder() {
        fun getService() = this@PlayerService
    }

    @Inject lateinit var audioManager: PlayerAudioManager
    @Inject lateinit var notification: PlayerNotification
    @Inject lateinit var prefs: Prefs
    @Inject lateinit var player: Player

    private val binder = PlayerBinder()
    private val audioNoisyReceiver = AudioNoisyReceiver()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        prefs.onAudioBalanceChangeListener = { player.balance = it }
        audioManager.onFocusLossListener = { player.pause() }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        DaggerPlayerServiceComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
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