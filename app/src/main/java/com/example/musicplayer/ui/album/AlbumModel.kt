package com.example.musicplayer.ui.album

import com.example.musicplayer.db.AlbumDB

class AlbumModel(albumDB: AlbumDB, artist: String) {
    val albums = albumDB.getAlbumsWithArtistTitle(artist)
}