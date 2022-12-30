package com.example.musicplayer.ui.album

import com.example.musicplayer.ui.ActivityScope
import com.example.musicplayer.db.AlbumDB
import dagger.Module
import dagger.Provides

@Module
class AlbumDaggerModule(private val activity: AlbumActivity) {
    @ActivityScope
    @Provides
    fun provideAlbumModel(albumDB: AlbumDB): AlbumModel {
        return AlbumModel(albumDB, activity.intent.getStringExtra(AlbumActivity.EXTRA_ARTIST)!!)
    }

    @ActivityScope
    @Provides
    fun provideAlbumPresenter(model: AlbumModel): AlbumPresenter {
        return AlbumPresenter(activity, model)
    }

    @ActivityScope
    @Provides
    fun provideAlbumAdapter(presenter: AlbumPresenter): AlbumAdapter {
        return AlbumAdapter(activity, presenter)
    }
}