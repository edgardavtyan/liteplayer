package com.example.musicplayer.ui.album

import com.example.musicplayer.db.AlbumDB
import javax.inject.Inject

class AlbumModel @Inject constructor(albumDB: AlbumDB, artist: String) {
    val albums = albumDB.getAlbumsWithArtistTitle(artist)
}