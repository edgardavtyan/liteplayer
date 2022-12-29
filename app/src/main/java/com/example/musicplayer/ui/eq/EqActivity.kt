package com.example.musicplayer.ui.eq

import android.app.Activity
import android.os.Bundle
import com.example.musicplayer.databinding.ActivityEqBinding
import javax.inject.Inject

class EqActivity: Activity() {
    @Inject lateinit var presenter: EqPresenter

    private lateinit var binding: ActivityEqBinding

    private var eqBands: ArrayList<EqBandView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerEqComponent
            .builder()
            .eqModule(EqModule(this))
            .build()
            .inject(this)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun setBandCount(count: Int) {
        eqBands = ArrayList()
        for (i in 0 until count) {
            val view = EqBandView(this, null)
            view.onProgressChangedListener = { presenter.onBandGainChanged(i, it) }
            binding.eq.addView(view)
            eqBands.add(view)
        }
    }

    fun setBandGain(band: Int, gain: Int) {
        eqBands[band].gain = gain
    }

    fun setBandFreq(band: Int, freq: Int) {
        eqBands[band].freq = freq
    }

    fun setMaxGain(maxGain: Int) {
        eqBands.forEach { it.maxGain = maxGain }
    }
}