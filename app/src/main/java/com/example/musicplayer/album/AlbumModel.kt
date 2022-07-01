package com.example.musicplayer.album

import android.content.Context
import com.example.musicplayer.db.Album
import com.example.musicplayer.db.AlbumDB

class AlbumModel(context: Context, artist: String) {
    private val albums = AlbumDB(context).getAlbumsWithArtistTitle(artist)

    val size = albums.size

    fun getAlbumIdAt(position: Int): Int {
        return albums[position].id
    }

    fun getAlbumAt(position: Int): Album {
        return albums[position]
    }
}