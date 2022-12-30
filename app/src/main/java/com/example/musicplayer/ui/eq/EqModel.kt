package com.example.musicplayer.ui.eq

import android.content.Context
import com.example.musicplayer.PlayerServiceConnection
import com.example.musicplayer.service.player.StandardEqualizer

class EqModel(context: Context): PlayerServiceConnection(context) {
    private lateinit var eq: StandardEqualizer

    val maxGain get() = eq.maxGain
    val freqs get() = (0 until eq.bandCount).map { eq.getBandFreq(it) }.toTypedArray()
    val gains get() = (0 until eq.bandCount).map { eq.getBandGain(it) }.toTypedArray()

    fun setBandGain(band: Int, gain: Int) {
        eq.setBandGain(band, gain)
    }

    override fun onServiceConnected() {
        eq = service.eq
        onDataLoaded?.invoke()
    }
}