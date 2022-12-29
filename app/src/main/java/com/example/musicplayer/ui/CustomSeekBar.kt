package com.example.musicplayer.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar

class CustomSeekBar(context: Context, attrs: AttributeSet): SeekBar(context, attrs) {
    var onProgressChangedListener: (Int) -> Unit = {}

    init {
        setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) onProgressChangedListener(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }
}