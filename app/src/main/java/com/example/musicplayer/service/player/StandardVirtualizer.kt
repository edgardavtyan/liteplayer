package com.example.musicplayer.service.player

import android.content.SharedPreferences
import com.h6ah4i.android.media.audiofx.IVirtualizer

class StandardVirtualizer(
    private val virtualizer: IVirtualizer,
    private val prefs: SharedPreferences
) {
    private val PREF_STRENGTH = "virtualizer-strength"

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