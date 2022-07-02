package com.example.musicplayer.ui.nowplaying

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.databinding.ActivityNowplayingBinding

class NowPlayingActivity : AppCompatActivity() {
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

    var trackTitle: CharSequence
        get() = binding.title.text
        set(text) { binding.title.text = text }

    var trackInfo: CharSequence
        get() = binding.info.text
        set(text) { binding.info.text = text }

    fun setPaused() {
        binding.btnPlaypause.setImageResource(android.R.drawable.ic_media_play)
    }

    fun setPlaying() {
        binding.btnPlaypause.setImageResource(android.R.drawable.ic_media_pause)
    }
}