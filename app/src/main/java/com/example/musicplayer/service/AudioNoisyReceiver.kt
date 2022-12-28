package com.example.musicplayer.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AudioNoisyReceiver(): BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.sendBroadcast(Intent(PlayerService.ACTION_AUDIO_NOISY))
    }
}