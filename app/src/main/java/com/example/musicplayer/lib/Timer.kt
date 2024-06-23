package com.example.musicplayer.lib

import android.os.Handler
import android.os.Looper

class Timer(interval: Long, runnable: Runnable) {
    private var stopHandler: Boolean = false

    private val callback: Runnable = object: Runnable {
        override fun run() {
            runnable.run()

            if (!stopHandler) {
                handler.postDelayed(this, interval)
            }
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    fun run() {
        stopHandler = false
        handler.post(callback)
    }

    fun stop() {
        stopHandler = true
    }
}