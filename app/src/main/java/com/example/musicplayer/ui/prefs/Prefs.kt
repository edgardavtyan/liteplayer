package com.example.musicplayer.ui.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(val context: Context) {
    private val PREF_AUDIO_BALANCE = "audio_balance"

    private val prefs = context.getSharedPreferences("prefs", MODE_PRIVATE)

    var audioBalance: Int
        get() = prefs.getInt(PREF_AUDIO_BALANCE, 20)
        set(balance) = prefs.edit().putInt(PREF_AUDIO_BALANCE, balance).apply()
}