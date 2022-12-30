package com.example.musicplayer.lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever

class CoverReader {
    private val mmr = MediaMetadataRetriever()

    fun getCover(path: String): Bitmap? {
        mmr.setDataSource(path)
        val data = mmr.embeddedPicture

        if (data != null) {
            return BitmapFactory.decodeByteArray(data, 0, data.size)
        } else {
            return null
        }
    }
}