package com.example.musicplayer

import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [MainDaggerModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}