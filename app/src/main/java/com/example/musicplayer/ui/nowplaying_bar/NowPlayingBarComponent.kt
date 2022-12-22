package com.example.musicplayer.ui.nowplaying_bar

import com.example.musicplayer.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [NowPlayingBarModule::class])
interface NowPlayingBarComponent {
    fun inject(fragment: NowPlayingBarFragment)
}