package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.IBinder
import com.example.musicplayer.service.player.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService: Service() {
    private val NOTIF_ID = 12345

    @Inject lateinit var audioManager: PlayerAudioManager
    @Inject lateinit var notification: PlayerNotification
    @Inject lateinit var audioNoisyReceiver: AudioNoisyReceiver
    @Inject lateinit var playPauseReceiver: PlayPauseReceiver
    @Inject lateinit var oldPlayer: Player
    @Inject lateinit var player: BassPlayer
    @Inject lateinit var eq: StandardEqualizer
    @Inject lateinit var virtualizer: StandardVirtualizer
    @Inject lateinit var binder: PlayerServiceBinder

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        audioManager.onFocusLossListener = { player.pause() }
        player.addOnIsPlayingChangedListener {
            updateNotificationPlayPause()
            startForeground(NOTIF_ID, notification.build())
        }
        player.addOnPreparedListener { updateNotification() }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIF_ID, notification.build())
        registerReceiver(audioNoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
        registerReceiver(playPauseReceiver, IntentFilter(PlayerNotification.ACTION_PLAY_PAUSE))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(audioNoisyReceiver)
    }

    private fun updateNotification() {
        notification.setTitle(player.track?.title)
        notification.setIsPlaying(player.isPlaying)
        startForeground(NOTIF_ID, notification.build())
    }

    private fun updateNotificationPlayPause() {
        notification.setIsPlaying(player.isPlaying)
        startForeground(NOTIF_ID, notification.build())
    }
}