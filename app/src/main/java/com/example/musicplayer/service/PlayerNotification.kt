package com.example.musicplayer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.example.musicplayer.R

class PlayerNotification(service: PlayerService) {
    val notification: Notification

    init {
        val chan = NotificationChannel("com.example.musicplayer", "channel", NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.GREEN
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)

        val intent = Intent(service, PlayerService::class.java)
        val pi = PendingIntent.getActivity(service, 0, intent, 0)
        notification = NotificationCompat.Builder(service, "com.example.musicplayer")
            .setSmallIcon(R.drawable.ic_status)
            .setContentTitle(service.getString(R.string.app_name))
            .setContentText("Running")
            .setContentIntent(pi)
            .build()
    }
}