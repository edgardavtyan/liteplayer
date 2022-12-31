package com.example.musicplayer.ui.prefs

import javax.inject.Inject

class PrefsPresenter @Inject constructor(
    private val view: PrefsActivity,
    private val model: PrefsModel)
{
    fun onCreate() {
        view.setAudioBalanceSeekBar(model.audioBalance)
        view.setAudioBalanceText(model.audioBalance)
        model.bind()
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onAudioBalanceChanged(balance: Int) {
        model.audioBalance = balance
        view.setAudioBalanceText(balance)
    }
}