package com.example.musicplayer.ui.prefs

class PrefsModel(private val prefs: Prefs) {
    var audioBalance: Int
        get() = prefs.getAudioBalance()
        set(balance) = prefs.setAudioBalance(balance)
}