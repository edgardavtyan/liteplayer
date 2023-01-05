package com.example.musicplayer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.musicplayer.R
import com.example.musicplayer.lib.IntentBuilder
import com.example.musicplayer.ui.artist.MainActivity

class PlayerNotification(service: PlayerService) {
    companion object {
        const val ACTION_PLAY_PAUSE = "action_playpause"
    }

    private val builder: NotificationCompat.Builder
    private val viewCompact = RemoteViews(service.packageName, R.layout.notif_compact)
    private val intentBuilder = IntentBuilder(service)

    init {
        val chan = NotificationChannel(service.packageName, "channel", NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.GREEN
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = (service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)

        builder = NotificationCompat.Builder(service, service.packageName)
            .setSmallIcon(R.drawable.ic_status)
            .setCustomContentView(viewCompact)
            .setContentIntent(intentBuilder.pendingActivity(MainActivity::class.java))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        viewCompact.setOnClickPendingIntent(
            R.id.btnPlayPause, intentBuilder.pendingBroadcast(ACTION_PLAY_PAUSE))
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