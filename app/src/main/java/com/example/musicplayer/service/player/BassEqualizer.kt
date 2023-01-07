package com.example.musicplayer.service.player

import android.content.SharedPreferences
import com.un4seen.bass.BASS

class BassEqualizer(audioChannel: Int, private val prefs: SharedPreferences) {
    private val PREF_GAINS = "eq-gains"

    private val bands = arrayListOf<BASS.BASS_DX8_PARAMEQ>()
    private val frequencies = arrayOf(31, 62, 125, 250, 500, 1000, 2000, 4000, 8000, 16000)
    private val gains: Array<Int>
    private val channels = Array(10) { it }

    init {
        gains = prefs.getString(PREF_GAINS, "0;0;0;0;0;0;0;0;0;0")!!
            .split(";")
            .map { it.toInt() }
            .toTypedArray()

        for (i in frequencies.indices) {
            channels[i] = BASS.BASS_ChannelSetFX(audioChannel, BASS.BASS_FX_DX8_PARAMEQ, 0)
            val band = BASS.BASS_DX8_PARAMEQ()
            band.fCenter = frequencies[i].toFloat()
            band.fBandwidth = 10f
            band.fGain = gains[i].toFloat()
            bands.add(band)
            BASS.BASS_FXSetParameters(channels[i], band)
        }
    }

    val minGain = -15
    val maxGain = 15
    val bandCount = 10

    fun setBandGain(band: Int, gain: Int) {
        bands[band].fGain = gain.toFloat()
        BASS.BASS_FXSetParameters(channels[band], bands[band])
        gains[band] = gain
        prefs.edit().putString(PREF_GAINS, gains.joinToString(";")).apply()
    }

    fun getBandGain(band: Int): Int {
        return bands[band].fGain.toInt()
    }

    fun getBandFreq(band: Int): Int {
        return frequencies[band]
    }

    fun update(sessionId: Int) {
        for (i in bands.indices) { channels[i] = BASS.BASS_ChannelSetFX(sessionId, BASS.BASS_FX_DX8_PARAMEQ, 0) }
        bands.forEachIndexed { i, band -> BASS.BASS_FXSetParameters(channels[i], band) }
    }
}