package com.example.musicplayer.ui.nowplaying_bar

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicplayer.R
import com.example.musicplayer.databinding.FragmentNowplayingBinding
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity
import com.example.musicplayer.ui.prefs.PrefsActivity
import javax.inject.Inject

class NowPlayingBarFragment: Fragment() {
    @Inject lateinit var presenter: NowPlayingBarPresenter

    private lateinit var binding: FragmentNowplayingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerNowPlayingBarComponent
            .builder()
            .nowPlayingBarModule(NowPlayingBarModule(this))
            .build()
            .inject(this)

        binding = FragmentNowplayingBinding.inflate(inflater)
        presenter.onCreateView()
        binding.btnPlayPause.setOnClickListener { presenter.onBtnPlayPauseClicked() }
        binding.btnSettings.setOnClickListener { presenter.onBtnSettingsClicked() }
        binding.title.setOnClickListener { presenter.onBarClicked() }
        binding.cover.setOnClickListener { presenter.onBarClicked() }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    fun setTitle(title: String?) {
        binding.title.text = title
    }

    fun setIsPlaying(isPlaying: Boolean) {
        val resId = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        binding.btnPlayPause.setImageResource(resId)
    }

    fun gotoPrefs() {
        requireContext().startActivity(Intent(requireContext(), PrefsActivity::class.java))
    }

    fun gotoNowPlaying() {
        requireContext().startActivity(Intent(requireContext(), NowPlayingActivity::class.java))
    }

    fun setCover(cover: Bitmap?) {
        binding.cover.setImageBitmap(cover)
    }
}