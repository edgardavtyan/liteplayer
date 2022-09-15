package com.example.musicplayer.ui.prefs

class PrefsPresenter(private val view: PrefsActivity, private val model: PrefsModel) {
    fun onCreate() {
        view.setAudioBalanceSeekBar(model.getAudioBalance())
        view.setAudioBalanceText(model.getAudioBalance())
    }

    fun onAudioBalanceChanged(balance: Int) {
        model.setAudioBalance(balance)
        view.setAudioBalanceText(balance)
    }
}