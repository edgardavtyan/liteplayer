package com.example.musicplayer.ui.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.musicplayer.lib.PlayerServiceConnection

class PrefsModel(context: Context, private val prefs: SharedPreferences)
    : PlayerServiceConnection(context)
{
    private val PREF_AUDIO_BALANCE = "audio-balance"

    var audioBalance: Int
        get() = prefs.getInt(PREF_AUDIO_BALANCE, 20)
        set(balance) {
            prefs.edit().putInt(PREF_AUDIO_BALANCE, balance).apply()
            service.player.balance = balance
        }
}