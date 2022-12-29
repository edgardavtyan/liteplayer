package com.example.musicplayer.ui.eq

import android.content.ComponentName
import android.content.Context
import android.os.IBinder
import com.example.musicplayer.PlayerServiceConnection
import com.example.musicplayer.service.player.StandardEqualizer

class EqModel(context: Context): PlayerServiceConnection(context) {
    private lateinit var eq: StandardEqualizer

    val maxGain get() = eq.maxGain

    val freqs get() = (0 until eq.bandCount).map { eq.getBandFreq(it) }.toTypedArray()

    val gains get() = (0 until eq.bandCount).map { eq.getBandGain(it) }.toTypedArray()

    var onServiceConnectedListener: (() -> Unit)? = null

    fun setBandGain(band: Int, gain: Int) {
        eq.setBandGain(band, gain)
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        super.onServiceConnected(name, binder)
        eq = service.eq
        onServiceConnectedListener?.invoke()
    }
}