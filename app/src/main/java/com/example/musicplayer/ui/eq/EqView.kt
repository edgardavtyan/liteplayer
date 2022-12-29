package com.example.musicplayer.ui.eq

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.musicplayer.databinding.PartialEqBandBinding

class EqView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes)
{
    private val binding = PartialEqBandBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.band.onProgressChangedListener = {
            val gain = it - maxGain
            val sign = if (gain > 0) "+" else ""
            binding.gain.text = "$sign$gain dB"
            onProgressChangedListener?.invoke(it)
        }
    }

    var onProgressChangedListener: ((Int) -> Unit)? = null

    var maxGain: Int
        get() = binding.band.max / 2
        set(gain) { binding.band.max = gain * 2 }

    var gain: Int
        get() = binding.band.progress - (maxGain)
        set(gain) {
            binding.band.progress = gain + (maxGain)
            val sign = if (gain > 0) "+" else ""
            binding.gain.text = "$sign$gain dB"
        }

    var freq: Int = 0
        set(freq) {
            if (freq >= 1000) {
                binding.freq.text = "${freq / 1000} kHz"
            } else {
                binding.freq.text = "$freq Hz"
            }
            field = freq
        }
}