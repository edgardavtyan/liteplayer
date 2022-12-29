package com.example.musicplayer.service.player

import android.media.audiofx.Equalizer
import com.example.musicplayer.ui.prefs.Prefs

class StandardEqualizer(private val eq: Equalizer, private val prefs: Prefs) {
    init {
        eq.enabled = true
        prefs.standardEqBands.forEachIndexed { i, gain -> setBandGain(i, gain) }
    }

    fun setBandGain(band: Int, gain: Int) {
        eq.setBandLevel(band.toShort(), (gain * 100).toShort())
        prefs.standardEqBands = (0 until 5).map { eq.getBandLevel(it.toShort()) / 100 }.toTypedArray()
    }

    fun getBandGain(band: Int): Int {
        return eq.getBandLevel(band.toShort()) / 100
    }

    fun getBandFreq(band: Int): Int {
        return eq.getCenterFreq(band.toShort()) / 1000
    }

    val minGain: Int get() = eq.bandLevelRange[0] / 100
    val maxGain: Int get() = eq.bandLevelRange[1] / 100
    val bandCount: Int get() = eq.numberOfBands.toInt()
}