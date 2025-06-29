package com.example.musicplayer.ui.eq

import android.content.Context
import com.example.musicplayer.lib.PlayerServiceConnection
import com.example.musicplayer.service.player.vanilla.VanillaEqualizer
import com.example.musicplayer.service.player.vanilla.VanillaVirtualizer

class EqModel(context: Context): PlayerServiceConnection(context) {
    private lateinit var eq: VanillaEqualizer
    private lateinit var virtualizer: VanillaVirtualizer

    val eqMaxGain get() = eq.maxGain
    val eqFreqs get() = (0 until eq.bandCount).map { eq.getBandFreq(it) }.toTypedArray()
    val eqGains get() = (0 until eq.bandCount).map { eq.getBandGain(it) }.toTypedArray()
    val virtualizerMaxStrength: Int get() = virtualizer.maxStrength
    var virtualizerStrength: Int
        get() = virtualizer.strength
        set(str) { virtualizer.strength = str }

    fun setEQBandGain(band: Int, gain: Int) {
        eq.setBandGain(band, gain)
    }

    override fun onServiceConnected() {
        eq = service.eq
        virtualizer = service.virtualizer
        onDataLoaded?.invoke()
    }
}