package com.example.musicplayer

import com.example.musicplayer.db.Artist
import com.example.musicplayer.db.ArtistDB

class MainModel(activity: MainActivity) {
    private val artistDB = ArtistDB(activity)

    fun getArtists(): List<Artist> {
        return artistDB.getAllArtists()
    }
}