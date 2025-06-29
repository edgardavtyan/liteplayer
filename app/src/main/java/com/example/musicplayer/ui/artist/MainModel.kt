package com.example.musicplayer.ui.artist

import android.content.Context
import com.example.musicplayer.db.Album
import com.example.musicplayer.db.DB
import com.example.musicplayer.db.Track
import com.example.musicplayer.lib.PlayerServiceConnection

class MainModel(context: Context, private val db: DB): PlayerServiceConnection(context) {
    val artists = db.artistDB.getAllArtists()

    var artist: String? = null; private set
    var albumId: Int? = null; private set

    fun albumCount(artist: String): Int {
        this.artist = artist
        return db.albumDB.getAlbumsWithArtistTitle(artist).size
    }

    fun getFirstAlbum(artist: String): Album {
        this.artist = artist
        return db.albumDB.getAlbumsWithArtistTitle(artist)[0]
    }

    fun loadAlbums(artist: String): List<Album> {
        this.artist = artist
        return db.albumDB.getAlbumsWithArtistTitle(artist)
    }

    fun loadTracks(albumId: Int): List<Track> {
        this.albumId = albumId
        return db.trackDB.getTracksWithAlbumId(albumId)
    }

    fun getAlbumAt(position: Int): Album {
        return db.albumDB.getAlbumsWithArtistTitle(artist!!)[position]
    }

    fun playTrack(position: Int) {
        service.player.playTrack(db.trackDB.getTracksWithAlbumId(albumId!!)[position])
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        onDataLoaded?.invoke()
    }
}