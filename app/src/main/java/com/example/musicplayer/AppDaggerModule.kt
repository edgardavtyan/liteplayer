package com.example.musicplayer

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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

    @Provides
    @Singleton
    fun provideSharedPrefs(app: Application): SharedPreferences {
        return app.getSharedPreferences("com.example.musicplayer", Context.MODE_PRIVATE)
    }
}