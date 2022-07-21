package com.example.musicplayer.service

import com.example.musicplayer.AppDaggerComponent
import com.example.musicplayer.player.PlayerModule
import dagger.Component

@Component(
    dependencies = [AppDaggerComponent::class],
    modules = [
        PlayerServiceModule::class,
        PlayerModule::class])
@PlayerServiceScope
interface PlayerServiceComponent {
    fun inject(service: PlayerService)
}