package com.example.musicplayer.db

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideArtistDB(context: Context): ArtistDB {
        return ArtistDB(context)
    }

    @Singleton
    @Provides
    fun provideAlbumDB(context: Context): AlbumDB {
        return AlbumDB(context)
    }

    @Singleton
    @Provides
    fun provideTrackDB(context: Context): TrackDB {
        return TrackDB(context)
    }
}