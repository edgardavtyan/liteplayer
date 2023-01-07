package com.example.musicplayer.ui.eq

class EqPresenter(private val view: EqActivity, private val model: EqModel) {
    fun onCreate() {
        model.bind()
        model.onDataLoaded = {
            view.setEQBandCount(model.eqGains.size)
            view.setEQMaxGain(model.eqMaxGain)
            model.eqGains.forEachIndexed { i, gain -> view.setEQBandGain(i, gain) }
            model.eqFreqs.forEachIndexed { i, freq -> view.setEQBandFreq(i, freq) }
        }
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onEQBandGainChanged(band: Int, gain: Int) {
        model.setEQBandGain(band, gain)
    }
}