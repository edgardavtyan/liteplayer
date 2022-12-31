package com.example.musicplayer.service

import android.os.Binder

class PlayerServiceBinder(val service: PlayerService): Binder()