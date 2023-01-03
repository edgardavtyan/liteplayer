package com.example.musicplayer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.musicplayer.R

class PlayerNotification(service: PlayerService) {
    companion object {
        const val ACTION_PLAY_PAUSE = "action_playpause"
    }

    private val viewCompact = RemoteViews(service.packageName, R.layout.notif_compact)

    private val builder: NotificationCompat.Builder

    init {
        val chan = NotificationChannel("com.example.musicplayer", "channel", NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.GREEN
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)

        val intent = Intent(service, PlayerService::class.java)
        val pi = PendingIntent.getActivity(service, 0, intent, 0)
        builder = NotificationCompat.Builder(service, "com.example.musicplayer")
            .setSmallIcon(R.drawable.ic_status)
            .setContentTitle(service.getString(R.string.app_name))
            .setCustomContentView(viewCompact)
            .setContentText("Running")
            .setContentIntent(pi)

        val playPauseIntent = Intent(ACTION_PLAY_PAUSE)
        val playPausePendingIntent = PendingIntent.getBroadcast(service, 0, playPauseIntent, 0)
        viewCompact.setOnClickPendingIntent(R.id.btnPlayPause, playPausePendingIntent)
    }

    fun build(): Notification {
        return builder.build()
    }

    fun setTitle(title: String?) {
        viewCompact.setTextViewText(R.id.title, title)
    }

    fun setIsPlaying(isPlaying: Boolean) {
        val resId = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        viewCompact.setImageViewResource(R.id.btnPlayPause, resId)
    }
}