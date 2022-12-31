package com.example.musicplayer.service

import android.app.Service
import android.media.MediaPlayer
import android.media.audiofx.Equalizer
import com.example.musicplayer.service.player.Player
import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.service.player.StandardEqualizer
import com.example.musicplayer.service.player.StandardPlayer
import com.example.musicplayer.ui.prefs.Prefs
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
        return StandardPlayer(MediaPlayer())
    }

    @Provides
    @ServiceScoped
    fun provideEqualizer(prefs: Prefs, player: Player): StandardEqualizer {
        return StandardEqualizer(Equalizer(0, player.sessionId), prefs)
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
    fun providePlayerServiceBinder(service: PlayerService): PlayerServiceBinder {
        return PlayerServiceBinder(service)
    }
}