package com.example.musicplayer.ui.album

import com.example.musicplayer.db.AlbumDB

class AlbumModel(albumDB: AlbumDB, val artist: String) {
    val albums = albumDB.getAlbumsWithArtistTitle(artist)
}