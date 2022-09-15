package com.example.musicplayer.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity


class GoToNowPlayingButton(context: Context, attr: AttributeSet): ImageView(context, attr) {
    init {
        setOnClickListener {
            val intent = Intent(context, NowPlayingActivity::class.java)
            context.startActivity(intent)
        }
    }
}