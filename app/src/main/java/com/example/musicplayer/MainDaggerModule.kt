package com.example.musicplayer

import android.content.Context
import com.example.musicplayer.db.ArtistDB
import dagger.Module
import dagger.Provides

@Module
class MainDaggerModule(private val activity: MainActivity) {
    @ActivityScope
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideMainModel(artistDB: ArtistDB): MainModel {
        return MainModel(artistDB)
    }

    @ActivityScope
    @Provides
    fun provideMainPresenter(model: MainModel): MainPresenter {
        return MainPresenter(model, activity)
    }

    @ActivityScope
    @Provides
    fun provideMainAdapter(presenter: MainPresenter, model: MainModel): MainAdapter {
        return MainAdapter(activity, presenter, model.artists)
    }
}