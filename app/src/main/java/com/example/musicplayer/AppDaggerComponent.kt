package com.example.musicplayer

import com.example.musicplayer.db.AlbumDB
import com.example.musicplayer.db.ArtistDB
import com.example.musicplayer.db.DbModule
import com.example.musicplayer.db.TrackDB
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppDaggerModule::class, DbModule::class])
interface AppDaggerComponent {
    fun artistDB(): ArtistDB
    fun albumDB(): AlbumDB
    fun trackDB(): TrackDB
}