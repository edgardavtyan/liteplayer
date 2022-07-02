package com.example.musicplayer.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.TextView
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity


class GoToNowPlayingButton(context: Context, attr: AttributeSet): TextView(context, attr) {
    init {
        setOnClickListener {
            val intent = Intent(context, NowPlayingActivity::class.java)
            context.startActivity(intent)
        }
    }
}