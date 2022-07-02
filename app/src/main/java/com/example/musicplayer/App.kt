package com.example.musicplayer

import android.app.Application
import com.example.musicplayer.db.DbModule

class App: Application() {
    var appComponent =
        DaggerAppDaggerComponent
            .builder()
            .appDaggerModule(AppDaggerModule(this))
            .dbModule(DbModule())
            .build()
}