package com.example.musicplayer.ui.prefs

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class PrefsModule {
    @Provides
    @ActivityScoped
    fun provideActivity(activity: Activity): PrefsActivity {
        return activity as PrefsActivity
    }

    @Provides
    @ActivityScoped
    fun provideModel(prefs: Prefs): PrefsModel {
        return PrefsModel(prefs)
    }

    @Provides
    @ActivityScoped
    fun providePresenter(activity: PrefsActivity, model: PrefsModel): PrefsPresenter {
        return PrefsPresenter(activity, model)
    }
}