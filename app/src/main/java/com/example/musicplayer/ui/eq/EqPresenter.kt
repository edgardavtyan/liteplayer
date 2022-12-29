package com.example.musicplayer.ui.eq

class EqPresenter(private val view: EqActivity, private val model: EqModel) {
    fun onCreate() {
        model.bind()
        model.onServiceConnectedListener = {
            view.setBandCount(model.gains.size)
            view.setMaxGain(model.maxGain)
            model.gains.forEachIndexed { i, gain -> view.setBandGain(i, gain) }
            model.freqs.forEachIndexed { i, freq -> view.setBandFreq(i, freq) }
        }
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onBandGainChanged(band: Int, gain: Int) {
        model.setBandGain(band, gain)
    }
}