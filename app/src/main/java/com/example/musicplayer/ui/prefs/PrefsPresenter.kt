package com.example.musicplayer.ui.prefs

class PrefsPresenter(private val view: PrefsActivity, private val model: PrefsModel) {
    fun onCreate() {
        view.setAudioBalanceSeekBar(model.audioBalance)
        view.setAudioBalanceText(model.audioBalance)
    }

    fun onAudioBalanceChanged(balance: Int) {
        model.audioBalance = balance
        view.setAudioBalanceText(balance)
    }
}