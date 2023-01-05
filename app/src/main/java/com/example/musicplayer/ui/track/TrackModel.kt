package com.example.musicplayer.ui.track

import android.content.Context
import com.example.musicplayer.db.TrackDB
import com.example.musicplayer.lib.PlayerServiceConnection

class TrackModel(context: Context, albumId: Int): PlayerServiceConnection(context) {
    var tracks = TrackDB(context).getTracksWithAlbumId(albumId)

    fun playTrack(position: Int) {
        service.player.playTrack(tracks[position])
    }

    fun addOnPreparedListener(listener: () -> Unit) {
        service.player.addOnPreparedListener(listener)
    }

    fun removeOnPreparedListener(listener: () -> Unit) {
        service.player.removeOnPreparedListener(listener)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        onDataLoaded?.invoke()
    }
}