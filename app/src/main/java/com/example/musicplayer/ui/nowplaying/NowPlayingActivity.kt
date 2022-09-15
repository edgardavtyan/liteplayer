package com.example.musicplayer.ui.nowplaying

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import com.example.musicplayer.R
import com.example.musicplayer.databinding.ActivityNowplayingBinding

class NowPlayingActivity : Activity() {
    private lateinit var binding: ActivityNowplayingBinding
    private lateinit var presenter: NowPlayingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNowplayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = NowPlayingPresenter(this, NowPlayingModel(this))
        presenter.onCreate()

        binding.btnPlaypause.setOnClickListener { presenter.onPlayPauseClick() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun setTrackTitle(title: CharSequence) {
        binding.title.text = title
    }

    fun setTrackInfo(info: CharSequence) {
        binding.info.text = info
    }

    fun setCoverArt(cover: Bitmap?) {
        if (cover != null) {
            binding.art.setImageBitmap(cover)
        }
    }

    fun setPaused() {
        binding.btnPlaypause.setImageResource(R.drawable.ic_play)
    }

    fun setPlaying() {
        binding.btnPlaypause.setImageResource(R.drawable.ic_pause)
    }
}