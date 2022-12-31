package com.example.musicplayer.db

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideArtistDB(app: Application): ArtistDB {
        return ArtistDB(app)
    }

    @Provides
    @Singleton
    fun provideAlbumDB(app: Application): AlbumDB {
        return AlbumDB(app)
    }

    @Provides
    @Singleton
    fun provideTrackDB(app: Application): TrackDB {
        return TrackDB(app)
    }
}