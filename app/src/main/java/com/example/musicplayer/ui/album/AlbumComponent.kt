package com.example.musicplayer.ui.album

import com.example.musicplayer.ActivityScope
import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [AlbumDaggerModule::class])
interface AlbumComponent {
    fun inject(activity: AlbumActivity)
}