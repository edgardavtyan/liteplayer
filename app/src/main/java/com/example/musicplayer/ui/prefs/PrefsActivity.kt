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
    @Inject lateinit var presenter: PrefsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPrefsComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .prefsModule(PrefsModule(this))
            .build()
            .inject(this)

        binding = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate()

        binding.audioBalanceSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.onAudioBalanceChanged(progress * 5 - 100)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }

    fun setAudioBalanceSeekBar(progress: Int) {
        binding.audioBalanceSeekBar.progress = progress / 5 + 20
    }

    fun setAudioBalanceText(progress: Int) {
        binding.audioBalanceTextView.text = progress.toString()
    }
}