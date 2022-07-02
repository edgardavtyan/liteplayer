package com.example.musicplayer

import com.example.musicplayer.db.ArtistDB

class MainModel(artistDB: ArtistDB) {
    val artists = artistDB.getAllArtists()
}