package com.example.musicplayer.service.player.vanilla

import android.content.SharedPreferences
import android.media.audiofx.Virtualizer
import com.h6ah4i.android.media.audiofx.IVirtualizer

class VanillaVirtualizer(sessionId: Int, private val prefs: SharedPreferences) {
    private val PREF_STRENGTH = "virtualizer-strength"

    private val virtualizer = Virtualizer(0, sessionId)

    init {
        virtualizer.enabled = true
        virtualizer.setStrength((prefs.getInt(PREF_STRENGTH, 0) * 10).toShort())
    }

    val maxStrength: Int = 100

    var strength: Int
        get() = virtualizer.roundedStrength / 10
        set(str) {
            virtualizer.setStrength((str * 10).toShort())
            prefs.edit().putInt(PREF_STRENGTH, str).apply()
        }
}