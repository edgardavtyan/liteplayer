package com.example.musicplayer.ui.artist

import com.example.musicplayer.db.ArtistDB

class MainModel(artistDB: ArtistDB) {
    val artists = artistDB.getAllArtists()
}