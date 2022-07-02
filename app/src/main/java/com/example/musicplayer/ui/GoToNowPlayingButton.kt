package com.example.musicplayer.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity


class GoToNowPlayingButton(context: Context, attr: AttributeSet): AppCompatTextView(context, attr) {
    init {
        setOnClickListener {
            val intent = Intent(context, NowPlayingActivity::class.java)
            context.startActivity(intent)
        }
    }
}