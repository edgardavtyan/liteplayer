package com.example.musicplayer.ui.prefs

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.musicplayer.databinding.ActivityPrefsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrefsActivity : FragmentActivity() {
    private lateinit var binding: ActivityPrefsBinding

    @Inject lateinit var prefs: Prefs
    @Inject lateinit var presenter: PrefsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrefsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate()

        binding.audioBalanceSeekBar.onProgressChangedListener = {
            presenter.onAudioBalanceChanged(it * 5 - 100)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun setAudioBalanceSeekBar(progress: Int) {
        binding.audioBalanceSeekBar.progress = progress / 5 + 20
    }

    fun setAudioBalanceText(progress: Int) {
        binding.audioBalanceTextView.text = progress.toString()
    }
}