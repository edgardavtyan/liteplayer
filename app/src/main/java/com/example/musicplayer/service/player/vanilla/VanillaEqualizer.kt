package com.example.musicplayer.service.player.vanilla

import android.content.SharedPreferences
import android.media.audiofx.Equalizer
import com.h6ah4i.android.media.audiofx.IEqualizer

class VanillaEqualizer(sessionId: Int, private val prefs: SharedPreferences) {
    private val PREF_GAINS = "eq-gains"

    private val eq = Equalizer(0, sessionId)

    init {
        eq.enabled = true
        getGainsFromPrefs().forEachIndexed { i, gain ->
            eq.setBandLevel(i.toShort(), (gain * 100).toShort())
        }
    }

    val minGain: Int get() = eq.bandLevelRange[0] / 100
    val maxGain: Int get() = eq.bandLevelRange[1] / 100
    val bandCount: Int get() = eq.numberOfBands.toInt()

    fun setBandGain(band: Int, gain: Int) {
        val newBand = bandCount - band - 1
        eq.setBandLevel(newBand.toShort(), (gain * 100).toShort())
        saveGainsToPrefs((0 until 5).map { eq.getBandLevel(it.toShort()) / 100 })
    }

    fun getBandGain(band: Int): Int {
        return eq.getBandLevel((bandCount - band - 1).toShort()) / 100
    }

    fun getBandFreq(band: Int): Int {
        return eq.getCenterFreq((bandCount - band - 1).toShort()) / 1000
    }

    private fun saveGainsToPrefs(gains: List<Int>) {
        prefs.edit().putString(PREF_GAINS, gains.joinToString(";")).apply()
    }

    private fun getGainsFromPrefs(): List<Int> {
        return prefs.getString(PREF_GAINS, "0;0;0;0;0")!!.split(";").map { it.toInt() }
    }
}