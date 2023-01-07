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
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun setEQBandCount(count: Int) {
        eqBands = ArrayList()
        for (i in 0 until count) {
            val view = EqBandView(this, null)
            view.onProgressChangedListener = { presenter.onEQBandGainChanged(i, it) }
            binding.eq.addView(view)
            eqBands.add(view)
        }
    }

    fun setEQBandGain(band: Int, gain: Int) {
        eqBands[band].setGain(gain)
    }

    fun setEQBandFreq(band: Int, freq: Int) {
        eqBands[band].setFreq(freq)
    }

    fun setEQMaxGain(maxGain: Int) {
        eqBands.forEach { it.maxGain = maxGain }
    }
}