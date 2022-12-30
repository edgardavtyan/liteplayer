package com.example.musicplayer.ui.artist

import android.content.Context
import com.example.musicplayer.db.AlbumDB
import com.example.musicplayer.db.ArtistDB
import com.example.musicplayer.ui.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {
    @ActivityScope
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideMainModel(artistDB: ArtistDB, albumDB: AlbumDB): MainModel {
        return MainModel(artistDB, albumDB)
    }

    @ActivityScope
    @Provides
    fun provideMainPresenter(model: MainModel): MainPresenter {
        return MainPresenter(model, activity)
    }

    @ActivityScope
    @Provides
    fun provideMainAdapter(presenter: MainPresenter): MainAdapter {
        return MainAdapter(activity, presenter)
    }
}