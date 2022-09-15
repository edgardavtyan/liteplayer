package com.example.musicplayer.ui.prefs

import android.app.Activity
import android.os.Bundle
import android.widget.SeekBar
import com.example.musicplayer.App
import com.example.musicplayer.databinding.ActivityPrefsBinding
import javax.inject.Inject

class PrefsActivity : Activity() {
    private lateinit var binding: ActivityPrefsBinding

    @Inject lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPrefsComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .build()
            .inject(this)

        binding = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.audioBalanceSeekBar.progress = prefs.getAudioBalance() / 5 + 20
        binding.audioBalanceTextView.text = prefs.getAudioBalance().toString()

        binding.audioBalanceSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefs.setAudioBalance(progress * 5 - 100)
                binding.audioBalanceTextView.text = (progress * 5 - 100).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }
}