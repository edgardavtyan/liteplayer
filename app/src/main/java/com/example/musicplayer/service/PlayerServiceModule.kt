package com.example.musicplayer.service

import com.example.musicplayer.player.Player
import dagger.Module
import dagger.Provides

@Module
class PlayerServiceModule(private val service: PlayerService) {
    @Provides
    @PlayerServiceScope
    fun provideAudioNoisyReceiver(player: Player): AudioNoisyReceiver {
        return AudioNoisyReceiver(player)
    }

    @Provides
    @PlayerServiceScope
    fun provideAudioManager(): PlayerAudioManager {
        return PlayerAudioManager(service)
    }

    @Provides
    @PlayerServiceScope
    fun provideNotification(): PlayerNotification {
        return PlayerNotification(service)
    }
}