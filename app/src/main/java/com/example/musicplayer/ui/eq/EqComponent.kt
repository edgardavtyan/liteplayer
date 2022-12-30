package com.example.musicplayer.ui.eq

import com.example.musicplayer.ui.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [EqModule::class])
interface EqComponent {
    fun inject(activity: EqActivity)
}