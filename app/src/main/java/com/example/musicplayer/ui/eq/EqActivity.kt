package com.example.musicplayer.ui.eq

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.musicplayer.databinding.ActivityEqBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EqActivity: FragmentActivity() {
    @Inject lateinit var presenter: EqPresenter

    private lateinit var binding: ActivityEqBinding

    private var eqBands: ArrayList<EqBandView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate()

        binding.virtualizerSeekBar.onProgressChangedListener = {
            presenter.onVirtualizerStrengthChanged(it) }
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
        eqBands[band].setGain(gain)
    }

    fun setBandFreq(band: Int, freq: Int) {
        eqBands[band].setFreq(freq)
    }

    fun setMaxGain(maxGain: Int) {
        eqBands.forEach { it.maxGain = maxGain }
    }

    fun setVirtualizerMaxStrength(maxStr: Int) {
        binding.virtualizerSeekBar.max = maxStr
    }

    fun setVirtualizerStrength(str: Int) {
        binding.virtualizerSeekBar.progress = str
    }
}