package com.example.musicplayer.ui.prefs

class PrefsModel(private val prefs: Prefs) {
    fun getAudioBalance(): Int {
        return prefs.getAudioBalance()
    }

    fun setAudioBalance(balance: Int) {
        prefs.setAudioBalance(balance)
    }
}