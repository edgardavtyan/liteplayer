package com.example.musicplayer.service

import android.media.MediaPlayer
import com.example.musicplayer.service.player.Player
import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.service.player.StandardPlayer
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
    fun providePlayer(): Player {
        return StandardPlayer(MediaPlayer())
    }

    @Provides
    @PlayerServiceScope
    fun provideNotification(): PlayerNotification {
        return PlayerNotification(service)
    }
}