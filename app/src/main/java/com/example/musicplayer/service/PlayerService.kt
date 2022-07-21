package com.example.musicplayer.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.musicplayer.App
import com.example.musicplayer.R
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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        val notifIntent = Intent(this, PlayerService::class.java)
        val pi = PendingIntent.getActivity(this, 0, notifIntent, 0)

        val notification: Notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Running")
            .setContentIntent(pi)
            .build()
        startForeground(101010, notification)

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