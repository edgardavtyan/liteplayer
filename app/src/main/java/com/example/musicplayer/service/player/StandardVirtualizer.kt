package com.example.musicplayer.service.player

import android.media.audiofx.Virtualizer

class StandardVirtualizer(private val virtualizer: Virtualizer) {
    init {
        virtualizer.enabled = true
    }

    val maxStrength: Int = 100

    var strength: Int
        set(str) { virtualizer.setStrength((str * 10).toShort()) }
        get() = virtualizer.roundedStrength / 10
}