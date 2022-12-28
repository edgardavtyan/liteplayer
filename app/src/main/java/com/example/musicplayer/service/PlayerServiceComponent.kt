package com.example.musicplayer.service

import com.example.musicplayer.AppDaggerComponent
import dagger.Component

@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [PlayerServiceModule::class])
@PlayerServiceScope
interface PlayerServiceComponent {
    fun inject(service: PlayerService)
}