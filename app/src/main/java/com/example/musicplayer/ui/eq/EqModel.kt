package com.example.musicplayer.ui.eq

import android.content.Context
import com.example.musicplayer.lib.PlayerServiceConnection
import com.example.musicplayer.service.player.StandardEqualizer
import com.example.musicplayer.service.player.StandardVirtualizer
import javax.inject.Inject

class EqModel @Inject constructor(context: Context): PlayerServiceConnection(context) {
    private lateinit var eq: StandardEqualizer
    private lateinit var virtualizer: StandardVirtualizer

    val maxGain get() = eq.maxGain
    val freqs get() = (0 until eq.bandCount).map { eq.getBandFreq(it) }.toTypedArray()
    val gains get() = (0 until eq.bandCount).map { eq.getBandGain(it) }.toTypedArray()
    val virtualizerMaxStrength: Int get() = virtualizer.maxStrength
    var virtualizerStrength: Int
        get() = virtualizer.strength
        set(str) { virtualizer.strength = str }

    fun setBandGain(band: Int, gain: Int) {
        eq.setBandGain(band, gain)
    }

    override fun onServiceConnected() {
        eq = service.eq
        virtualizer = service.virtualizer
        onDataLoaded?.invoke()
    }
}