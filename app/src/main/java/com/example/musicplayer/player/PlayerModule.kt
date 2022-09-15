package com.example.musicplayer.player

import com.example.musicplayer.service.PlayerServiceScope
import com.example.musicplayer.ui.prefs.Prefs
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {
    @Provides
    @PlayerServiceScope
    fun providesPlayer(prefs: Prefs): Player {
        return Player(prefs)
    }
}