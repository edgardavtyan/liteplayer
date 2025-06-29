package com.example.musicplayer.lib

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class IntentBuilder(private val context: Context) {
    fun <T> pendingActivity(clazz: Class<T>): PendingIntent {
        val intent = Intent(context, clazz)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun pendingBroadcast(action: String): PendingIntent {
        return PendingIntent.getBroadcast(context, 0, Intent(action), PendingIntent.FLAG_IMMUTABLE)
    }
}