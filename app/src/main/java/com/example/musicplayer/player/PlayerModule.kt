package com.example.musicplayer.player

import android.media.MediaPlayer
import com.example.musicplayer.service.PlayerAudioManager
import com.example.musicplayer.service.PlayerServiceScope
import com.example.musicplayer.ui.prefs.Prefs
import dagger.Module
import dagger.Provides

@Module
class PlayerModule() {
    @Provides
    @PlayerServiceScope
    fun providesPlayer(prefs: Prefs, audioManager: PlayerAudioManager, engine: AudioEngine): Player {
        return Player(prefs, engine, audioManager)
    }

    @Provides
    @PlayerServiceScope
    fun provideAudioEngine(): AudioEngine {
        return StandardAudioEngine(MediaPlayer())
    }
}