package com.example.musicplayer.ui.album

import android.app.Activity
import com.example.musicplayer.db.AlbumDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class AlbumModule {
    @Provides
    @ActivityScoped
    fun provideActivity(activity: Activity): AlbumActivity {
        return activity as AlbumActivity
    }


    @Provides
    @ActivityScoped
    fun provideAlbumModel(activity: AlbumActivity, albumDB: AlbumDB): AlbumModel {
        return AlbumModel(albumDB, activity.intent.getStringExtra(AlbumActivity.EXTRA_ARTIST)!!)
    }

    @Provides
    @ActivityScoped
    fun provideAlbumPresenter(activity: AlbumActivity, model: AlbumModel): AlbumPresenter {
        return AlbumPresenter(activity, model)
    }

    @Provides
    @ActivityScoped
    fun provideAlbumAdapter(presenter: AlbumPresenter): AlbumAdapter {
        return AlbumAdapter(presenter)
    }
}