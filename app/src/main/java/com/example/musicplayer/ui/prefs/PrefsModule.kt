package com.example.musicplayer.ui.prefs

import com.example.musicplayer.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class PrefsModule(private val view: PrefsActivity) {
    @Provides
    @ActivityScope
    fun provideModel(prefs: Prefs): PrefsModel {
        return PrefsModel(prefs)
    }

    @Provides
    @ActivityScope
    fun providePresenter(model: PrefsModel): PrefsPresenter {
        return PrefsPresenter(view, model)
    }
}