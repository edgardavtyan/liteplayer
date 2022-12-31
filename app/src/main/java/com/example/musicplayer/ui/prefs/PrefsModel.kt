package com.example.musicplayer.ui.prefs

import com.example.musicplayer.lib.PlayerServiceConnection
import javax.inject.Inject

class PrefsModel @Inject constructor(private val prefs: Prefs)
    : PlayerServiceConnection(prefs.context)
{
    var audioBalance: Int
        get() = prefs.audioBalance
        set(balance) {
            prefs.audioBalance = balance
            service.player.balance = balance
        }
}