package com.example.musicplayer.ui.eq

import android.content.Context
import com.example.musicplayer.lib.PlayerServiceConnection
import com.example.musicplayer.service.player.BassEqualizer

class EqModel(context: Context): PlayerServiceConnection(context) {
    private lateinit var eq: BassEqualizer

    val eqMaxGain get() = eq.maxGain
    val eqFreqs get() = (0 until eq.bandCount).map { eq.getBandFreq(it) }.toTypedArray()
    val eqGains get() = (0 until eq.bandCount).map { eq.getBandGain(it) }.toTypedArray()

    fun setEQBandGain(band: Int, gain: Int) {
        eq.setBandGain(band, gain)
    }

    override fun onServiceConnected() {
        eq = service.eq
        onDataLoaded?.invoke()
    }
}