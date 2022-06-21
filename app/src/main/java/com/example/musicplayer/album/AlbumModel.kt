package com.example.musicplayer.album

import android.content.Context
import com.example.musicplayer.db.Album
import com.example.musicplayer.db.AlbumDB

class AlbumModel(context: Context) {
    private val albumDB = AlbumDB(context)

    fun getArtistAlbums(artist: String): List<Album> {
        return albumDB.getAlbumsWithArtistTitle(artist)
    }
}