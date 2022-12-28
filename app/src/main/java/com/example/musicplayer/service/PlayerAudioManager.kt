package com.example.musicplayer.service

import android.content.Context
import android.media.AudioManager

class PlayerAudioManager(context: Context) : AudioManager.OnAudioFocusChangeListener {

    private val manager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    var onFocusLossListener: (() -> Unit)? = null

    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS -> onFocusLossListener?.invoke()
        }
    }

    fun setFocused(isPlaying: Boolean) {
        if (isPlaying) {
            manager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        } else {
            manager.abandonAudioFocus(this)
        }
    }
}