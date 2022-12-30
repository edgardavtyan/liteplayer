package com.example.musicplayer.ui.track

import com.example.musicplayer.ui.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class TrackModule(private val activity: TrackActivity) {
    @ActivityScope
    @Provides
    fun providesTrackModel(): TrackModel {
        return TrackModel(activity, activity.intent.getIntExtra(TrackActivity.EXTRA_ALBUM, -1))
    }

    @ActivityScope
    @Provides
    fun providesTrackPresenter(model: TrackModel): TrackPresenter {
        return TrackPresenter(activity, model)
    }

    @ActivityScope
    @Provides
    fun providesAdapter(presenter: TrackPresenter): TrackAdapter {
        return TrackAdapter(activity, presenter)
    }
}