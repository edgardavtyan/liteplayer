package com.example.musicplayer

import android.app.Application
import com.example.musicplayer.ui.prefs.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDaggerModule {
    @Provides
    @Singleton
    fun providePrefs(app: Application): Prefs {
        return Prefs(app)
    }
}