package com.example.musicplayer.service

import android.app.Service
import android.content.SharedPreferences
import com.example.musicplayer.service.player.*
import com.h6ah4i.android.media.IMediaPlayerFactory
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerContext
import com.h6ah4i.android.media.opensl.OpenSLMediaPlayerFactory
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
    fun providePlayerFactory(service: PlayerService): IMediaPlayerFactory {
        val params = OpenSLMediaPlayerContext.Parameters()
        params.options =
            OpenSLMediaPlayerContext.OPTION_USE_HQ_EQUALIZER or
            OpenSLMediaPlayerContext.OPTION_USE_VIRTUALIZER
        params.longFadeDuration = 0
        params.shortFadeDuration = 0
        return OpenSLMediaPlayerFactory(service, params)

    }

    @Provides
    @ServiceScoped
    fun provideAudioManager(service: PlayerService): PlayerAudioManager {
        return PlayerAudioManager(service)
    }

    @Provides
    @ServiceScoped
    fun providePlayer(factory: IMediaPlayerFactory): Player {
        return StandardPlayer(factory.createMediaPlayer())
    }

    @Provides
    @ServiceScoped
    fun provideEqualizer(
        prefs: SharedPreferences,
        factory: IMediaPlayerFactory,
        player: Player
    ): StandardEqualizer {
        return StandardEqualizer(factory.createHQEqualizer(), prefs)
    }

    @Provides
    @ServiceScoped
    fun provideVirtualizer(
        prefs: SharedPreferences,
        factory: IMediaPlayerFactory,
        player: Player
    ): StandardVirtualizer {
        return StandardVirtualizer(factory.createVirtualizer(player.sessionId), prefs)
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