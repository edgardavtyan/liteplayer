package com.example.musicplayer.service

import com.example.musicplayer.player.Player
import dagger.Module
import dagger.Provides

@Module
class PlayerServiceModule {
    @Provides
    @PlayerServiceScope
    fun provideAudioNoisyReceiver(player: Player): AudioNoisyReceiver {
        return AudioNoisyReceiver(player)
    }
}