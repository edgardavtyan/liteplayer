package com.example.musicplayer.ui.prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val PREF_AUDIO_BALANCE = "audio_balance"

    private val prefs = context.getSharedPreferences("prefs", MODE_PRIVATE)

    var onAudioBalanceChangeListener: (Int) -> Unit = {}

    fun setAudioBalance(balance: Int) {
        prefs.edit().putInt(PREF_AUDIO_BALANCE, balance).apply()
        onAudioBalanceChangeListener(balance)
    }

    fun getAudioBalance(): Int {
        return prefs.getInt(PREF_AUDIO_BALANCE, 20)
    }
}