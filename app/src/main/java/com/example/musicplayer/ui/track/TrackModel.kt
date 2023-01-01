package com.example.musicplayer.ui.track

import android.content.Context
import com.example.musicplayer.db.TrackDB
import com.example.musicplayer.lib.PlayerServiceConnection

class TrackModel(context: Context, albumId: Int): PlayerServiceConnection(context) {
    var tracks = TrackDB(context).getTracksWithAlbumId(albumId)

    fun playTrack(position: Int) {
        service.player.playTrack(tracks[position])
    }
}