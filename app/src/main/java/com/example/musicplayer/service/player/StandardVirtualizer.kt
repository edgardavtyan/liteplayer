package com.example.musicplayer.service.player

import android.media.audiofx.Virtualizer
import com.example.musicplayer.ui.prefs.Prefs

class StandardVirtualizer(private val virtualizer: Virtualizer, private val prefs: Prefs) {
    init {
        virtualizer.enabled = true
        virtualizer.setStrength((prefs.virtualizerStrength * 10).toShort())
    }

    val maxStrength: Int = 100

    var strength: Int
        get() = virtualizer.roundedStrength / 10
        set(str) {
            virtualizer.setStrength((str * 10).toShort())
            prefs.virtualizerStrength = str
        }
}