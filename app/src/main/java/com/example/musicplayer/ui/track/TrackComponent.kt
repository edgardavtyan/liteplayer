package com.example.musicplayer.ui.track

import com.example.musicplayer.ui.ActivityScope
import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [TrackModule::class])
interface TrackComponent {
    fun inject(activity: TrackActivity)
}