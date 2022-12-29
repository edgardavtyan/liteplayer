package com.example.musicplayer.service

import android.media.MediaPlayer
import android.media.audiofx.Equalizer
import com.example.musicplayer.service.player.Player
import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.service.player.StandardEqualizer
import com.example.musicplayer.service.player.StandardPlayer
import com.example.musicplayer.ui.prefs.Prefs
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
    fun provideEqualizer(prefs: Prefs, player: Player): StandardEqualizer {
        return StandardEqualizer(Equalizer(0, player.sessionId), prefs)
    }

    @Provides
    @PlayerServiceScope
    fun provideNotification(): PlayerNotification {
        return PlayerNotification(service)
    }
}