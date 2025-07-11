package com.example.musicplayer.ui.artist

import android.app.Activity
import com.example.musicplayer.db.AlbumDB
import com.example.musicplayer.db.ArtistDB
import com.example.musicplayer.db.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class MainModule {
    @Provides
    @ActivityScoped
    fun provideActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }

    @Provides
    @ActivityScoped
    fun provideMainModel(activity: MainActivity, db: DB): MainModel {
        return MainModel(activity, db)
    }

    @Provides
    @ActivityScoped
    fun provideMainPresenter(activity: MainActivity, model: MainModel): MainPresenter {
        return MainPresenter(model, activity)
    }

    @Provides
    @ActivityScoped
    fun provideMainAdapter(presenter: MainPresenter): MainAdapter {
        return MainAdapter(presenter)
    }
}