package com.example.musicplayer.service

import android.app.Service
import android.content.SharedPreferences
import com.example.musicplayer.lib.CoverReader
import com.example.musicplayer.service.player.*
import com.example.musicplayer.service.player.vanilla.VanillaEqualizer
import com.example.musicplayer.service.player.vanilla.VanillaPlayer
import com.example.musicplayer.service.player.vanilla.VanillaVirtualizer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
class PlayerServiceModule {
    @Provides
    @ServiceScoped
    fun provideService(service: Service): PlayerService {
        return service as PlayerService
    }

    @Provides
    @ServiceScoped
    fun provideAudioManager(service: PlayerService): PlayerAudioManager {
        return PlayerAudioManager(service)
    }

    @Provides
    @ServiceScoped
    fun providePlayer(): Player {
        return VanillaPlayer(CoverReader())
    }

    @Provides
    @ServiceScoped
    fun provideEqualizer(player: Player, prefs: SharedPreferences): VanillaEqualizer {
        return VanillaEqualizer(player.sessionId, prefs)
    }

    @Provides
    @ServiceScoped
    fun provideVirtualizer(player: Player, prefs: SharedPreferences): VanillaVirtualizer {
        return VanillaVirtualizer(player.sessionId, prefs)
    }

    @Provides
    @ServiceScoped
    fun provideNotification(service: PlayerService): PlayerNotification {
        return PlayerNotification(service)
    }

    @Provides
    @ServiceScoped
    fun provideAudioNoisyReceiver(player: Player): AudioNoisyReceiver {
        return AudioNoisyReceiver(player)
    }

    @Provides
    @ServiceScoped
    fun providePlayPauseReceiver(service: PlayerService): PlayPauseReceiver {
        return PlayPauseReceiver(service)
    }

    @Provides
    @ServiceScoped
    fun providePlayerServiceBinder(service: PlayerService): PlayerServiceBinder {
        return PlayerServiceBinder(service)
    }
}