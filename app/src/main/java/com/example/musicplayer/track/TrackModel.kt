package com.example.musicplayer.track

import android.content.Context
import com.example.musicplayer.db.Track
import com.example.musicplayer.db.TrackDB

class TrackModel(context: Context) {
    private val trackDB = TrackDB(context)

    fun getAlbumTracks(albumId: Int): List<Track> {
        return trackDB.getTracksWithAlbumId(albumId)
    }
}