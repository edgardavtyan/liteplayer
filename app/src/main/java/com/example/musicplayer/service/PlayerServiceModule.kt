package com.example.musicplayer.service

import android.media.MediaPlayer
import com.example.musicplayer.player.AudioEngine
import com.example.musicplayer.player.PlayerAudioManager
import com.example.musicplayer.player.StandardAudioEngine
import dagger.Module
import dagger.Provides

@Module
class PlayerServiceModule(private val service: PlayerService) {
    @Provides
    @PlayerServiceScope
    fun provideAudioManager(): PlayerAudioManager {
        return PlayerAudioManager(service)
    }

    @Provides
    @PlayerServiceScope
    fun providePlayer(): AudioEngine {
        return StandardAudioEngine(MediaPlayer())
    }

    @Provides
    @PlayerServiceScope
    fun provideNotification(): PlayerNotification {
        return PlayerNotification(service)
    }
}