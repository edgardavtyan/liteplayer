package com.example.musicplayer.ui.artist

import com.example.musicplayer.ui.ActivityScope
import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [MainDaggerModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}