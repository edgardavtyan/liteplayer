package com.example.musicplayer.service.player

import android.media.audiofx.Equalizer
import com.example.musicplayer.ui.prefs.Prefs

class StandardEqualizer(private val eq: Equalizer, private val prefs: Prefs) {
    fun setBandGain(band: Int, gain: Int) {
        eq.setBandLevel(band.toShort(), (gain * 1000).toShort())
        prefs.standardEqBands = (0 until 5).map { eq.getBandLevel(it.toShort()) / 1000 }.toTypedArray()
    }

    fun getBandGain(band: Int): Int {
        return eq.getBandLevel(band.toShort()) / 1000
    }

    fun getBandFreq(band: Int): Int {
        return eq.getCenterFreq(band.toShort())
    }

    val minGain: Int get() = eq.bandLevelRange[0] / 1000
    val maxGain: Int get() = eq.bandLevelRange[1] / 1000
    val bandCount: Int get() = eq.numberOfBands.toInt()
}