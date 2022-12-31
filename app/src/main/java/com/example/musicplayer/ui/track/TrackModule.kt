package com.example.musicplayer.ui.track

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class TrackModule {
    @Provides
    @ActivityScoped
    fun provideActivity(activity: Activity): TrackActivity {
        return activity as TrackActivity
    }

    @Provides
    @ActivityScoped
    fun providesTrackModel(activity: TrackActivity): TrackModel {
        return TrackModel(activity, activity.intent.getIntExtra(TrackActivity.EXTRA_ALBUM, -1))
    }

    @Provides
    @ActivityScoped
    fun providesTrackPresenter(activity: TrackActivity, model: TrackModel): TrackPresenter {
        return TrackPresenter(activity, model)
    }

    @Provides
    @ActivityScoped
    fun providesAdapter(activity: TrackActivity, presenter: TrackPresenter): TrackAdapter {
        return TrackAdapter(activity, presenter)
    }
}