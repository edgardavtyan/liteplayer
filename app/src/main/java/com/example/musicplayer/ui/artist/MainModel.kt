package com.example.musicplayer.ui.artist

import com.example.musicplayer.db.Album
import com.example.musicplayer.db.AlbumDB
import com.example.musicplayer.db.ArtistDB

class MainModel(artistDB: ArtistDB, private val albumDB: AlbumDB) {
    val artists = artistDB.getAllArtists()

    fun albumCount(artist: String): Int {
        return albumDB.getAlbumsWithArtistTitle(artist).size
    }

    fun getFirstAlbum(artist: String): Album {
        return albumDB.getAlbumsWithArtistTitle(artist)[0]
    }
}