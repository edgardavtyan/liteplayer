package com.example.musicplayer.db

import android.graphics.Bitmap

class Track {
    var id = 0
    var track = 0
    var title: String = ""
    var path: String = ""
    var dateModified: Long = 0
    var duration: Long = 0
    var artistId = 0
    var artistTitle: String? = null
    var albumId = 0
    var albumTitle: String? = null
    var queueIndex = 0
    var cover: Bitmap? = null
}