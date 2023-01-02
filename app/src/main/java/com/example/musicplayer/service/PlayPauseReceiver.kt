package com.example.musicplayer.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PlayPauseReceiver(private val service: PlayerService): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        service.player.playPause()
    }
}