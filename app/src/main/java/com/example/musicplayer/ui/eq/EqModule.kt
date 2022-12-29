package com.example.musicplayer.ui.eq

import com.example.musicplayer.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class EqModule(private val view: EqActivity) {
    @Provides
    @ActivityScope
    fun providePresenter(model: EqModel): EqPresenter {
        return EqPresenter(view, model)
    }

    @Provides
    @ActivityScope
    fun provideModel(): EqModel {
        return EqModel(view)
    }
}