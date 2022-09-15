package com.example.musicplayer

import android.content.Context
import com.example.musicplayer.ui.prefs.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDaggerModule(private val app: App) {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun providePrefs(context: Context): Prefs {
        return Prefs(context)
    }
}