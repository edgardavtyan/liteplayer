package com.example.musicplayer.ui.nowplaying_bar

import androidx.fragment.app.Fragment
import com.example.musicplayer.lib.CoverReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class NowPlayingBarModule() {
    @Provides
    @FragmentScoped
    fun provideFragment(fragment: Fragment): NowPlayingBarFragment {
        return fragment as NowPlayingBarFragment
    }

    @Provides
    @FragmentScoped
    fun provideModel(fragment: NowPlayingBarFragment): NowPlayingBarModel {
        return NowPlayingBarModel(fragment.requireContext(), CoverReader())
    }

    @Provides
    @FragmentScoped
    fun providePresenter(fragment: NowPlayingBarFragment, model: NowPlayingBarModel)
    : NowPlayingBarPresenter {
        return NowPlayingBarPresenter(fragment, model)
    }
}