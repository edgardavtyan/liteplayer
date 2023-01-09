package com.example.musicplayer.ui.nowplaying

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.musicplayer.R
import com.example.musicplayer.databinding.ActivityNowplayingBinding
import java.text.SimpleDateFormat
import java.util.*

class NowPlayingActivity : FragmentActivity() {
    private lateinit var binding: ActivityNowplayingBinding
    private lateinit var presenter: NowPlayingPresenter

    private val seekFormatHours = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    private val seekFormatMinutes = SimpleDateFormat("mm:ss", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNowplayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = NowPlayingPresenter(this, NowPlayingModel(this))
        presenter.onCreate()

        binding.btnPlaypause.setOnClickListener { presenter.onPlayPauseClick() }
        binding.seekBar.onProgressChangedListener = { presenter.onSeekChanged(it) }
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
            binding.artFallback.visibility = View.GONE
            binding.art.visibility = View.VISIBLE
            binding.art.setImageBitmap(cover)
        } else {
            binding.artFallback.visibility = View.VISIBLE
            binding.art.visibility = View.GONE
        }
    }

    fun setPaused() {
        binding.btnPlaypause.setImageResource(R.drawable.ic_play)
    }

    fun setPlaying() {
        binding.btnPlaypause.setImageResource(R.drawable.ic_pause)
    }

    fun setSeek(seek: Int) {
        val format = if (seek >= 3600) seekFormatHours else seekFormatMinutes
        binding.seekCurrent.text = format.format(Date(seek.toLong() * 1000))
        binding.seekBar.progress = seek
    }

    fun setSeekMax(seekMax: Int) {
        val format = if (seekMax >= 3600) seekFormatHours else seekFormatMinutes
        binding.seekBar.max = seekMax
        binding.seekTotal.text = format.format(Date(seekMax.toLong() * 1000))
    }
}