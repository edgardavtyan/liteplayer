package com.example.musicplayer.ui.track

import android.content.Context
import com.example.musicplayer.PlayerServiceConnection
import com.example.musicplayer.db.TrackDB

class TrackModel(context: Context, albumId: Int): PlayerServiceConnection(context) {
    var tracks = TrackDB(context).getTracksWithAlbumId(albumId)

    fun playTrack(position: Int) {
        service.player.playTrack(tracks[position])
    }
}