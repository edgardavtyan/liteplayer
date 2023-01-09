package com.example.musicplayer.lib

import android.os.Handler
import android.os.Looper

class Timer(private val interval: Long, action: Runnable) {
    val handler = Handler(Looper.getMainLooper())

    val runnable = object : Runnable {
        override fun run() {
            action.run()
            handler.postDelayed(this, interval)
        }
    }

    fun run() {
        handler.postDelayed(runnable, interval)
    }
}