package com.example.musicplayer.ui.nowplaying

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.R

class NowPlayingActivity : AppCompatActivity() {
    private lateinit var titleView: TextView
    private lateinit var infoView: TextView
    private lateinit var btnPrevView: ImageButton
    private lateinit var btnPlayPauseView: ImageButton
    private lateinit var btnNextView: ImageButton

    private lateinit var presenter: NowPlayingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nowplaying)

        titleView = findViewById(R.id.title)
        infoView = findViewById(R.id.info)
        btnPrevView = findViewById(R.id.btn_prev)
        btnPlayPauseView = findViewById(R.id.btn_playpause)
        btnNextView = findViewById(R.id.btn_next)

        presenter = NowPlayingPresenter(this, NowPlayingModel(this))
        presenter.onCreate()

        btnPlayPauseView.setOnClickListener { presenter.onPlayPauseClick() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    var trackTitle: CharSequence
        get() = titleView.text
        set(text) { titleView.text = text }

    var trackInfo: CharSequence
        get() = infoView.text
        set(text) { infoView.text = text }

    fun setPaused() {
        btnPlayPauseView.setImageResource(android.R.drawable.ic_media_play)
    }

    fun setPlaying() {
        btnPlayPauseView.setImageResource(android.R.drawable.ic_media_pause)
    }
}