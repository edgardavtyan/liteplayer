package com.example.musicplayer.ui.prefs

import com.example.musicplayer.ui.ActivityScope
import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [PrefsModule::class])
interface PrefsComponent {
    fun inject(activity: PrefsActivity)
}