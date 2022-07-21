package com.example.musicplayer.player

import com.example.musicplayer.service.PlayerServiceScope
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {
    @Provides
    @PlayerServiceScope
    fun providesPlayer(): Player {
        return Player()
    }
}