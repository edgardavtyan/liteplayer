package com.example.musicplayer.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.musicplayer.player.Player

class AudioNoisyReceiver(private val player: Player): BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        player.pause()
    }
}