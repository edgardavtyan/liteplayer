package com.example.musicplayer.ui.nowplaying_bar

import com.example.musicplayer.FragmentScope
import com.example.musicplayer.ui.CoverReader
import dagger.Module
import dagger.Provides

@Module
class NowPlayingBarModule(private val fragment: NowPlayingBarFragment) {
    @Provides
    @FragmentScope
    fun provideModel(): NowPlayingBarModel {
        return NowPlayingBarModel(fragment.requireContext(), CoverReader())
    }

    @Provides
    @FragmentScope
    fun providePresenter(model: NowPlayingBarModel): NowPlayingBarPresenter {
        return NowPlayingBarPresenter(fragment, model)
    }
}