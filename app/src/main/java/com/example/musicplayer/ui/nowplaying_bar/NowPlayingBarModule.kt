package com.example.musicplayer.ui.nowplaying_bar

import com.example.musicplayer.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class NowPlayingBarModule(private val fragment: NowPlayingBarFragment) {
    @Provides
    @FragmentScope
    fun provideModel(): NowPlayingBarModel {
        return NowPlayingBarModel(fragment.requireContext())
    }

    @Provides
    @FragmentScope
    fun providePresenter(model: NowPlayingBarModel): NowPlayingBarPresenter {
        return NowPlayingBarPresenter(fragment, model)
    }
}