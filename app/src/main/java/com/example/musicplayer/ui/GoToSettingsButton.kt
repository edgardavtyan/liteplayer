package com.example.musicplayer.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import com.example.musicplayer.ui.prefs.PrefsActivity

class GoToSettingsButton(context: Context, attr: AttributeSet): ImageView(context, attr) {
    init {
        setOnClickListener {
            val intent = Intent(context, PrefsActivity::class.java)
            context.startActivity(intent)
        }
    }
}