package com.example.musicplayer.ui.album

import com.example.musicplayer.ui.ActivityScope
import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [AlbumModule::class])
interface AlbumComponent {
    fun inject(activity: AlbumActivity)
}