package com.example.musicplayer.ui.eq

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class EqModule {
    @Provides
    @ActivityScoped
    fun provideActivity(activity: Activity): EqActivity {
        return activity as EqActivity
    }

    @Provides
    @ActivityScoped
    fun providePresenter(activity: EqActivity, model: EqModel): EqPresenter {
        return EqPresenter(activity, model)
    }

    @Provides
    @ActivityScoped
    fun provideModel(activity: EqActivity): EqModel {
        return EqModel(activity)
    }
}