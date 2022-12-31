package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.IBinder
import com.example.musicplayer.service.player.Player
import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.service.player.StandardEqualizer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService: Service() {
    @Inject lateinit var audioManager: PlayerAudioManager
    @Inject lateinit var notification: PlayerNotification
    @Inject lateinit var audioNoisyReceiver: AudioNoisyReceiver
    @Inject lateinit var player: Player
    @Inject lateinit var eq: StandardEqualizer
    @Inject lateinit var binder: PlayerServiceBinder

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        audioManager.onFocusLossListener = { player.pause() }
        player.addOnIsPlayingChangedListener { audioManager.setFocused(it) }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        startForeground(101010, notification.notification)
        registerReceiver(audioNoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioNoisyReceiver)
    }
}