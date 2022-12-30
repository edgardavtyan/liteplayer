package com.example.musicplayer.ui.prefs

import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder
import com.example.musicplayer.service.player.Player
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestPrefsModel {
    @MockK lateinit var prefs: Prefs
    @MockK lateinit var binder: PlayerServiceBinder
    @MockK lateinit var service: PlayerService
    @MockK lateinit var player: Player

    private lateinit var model: PrefsModel

    @BeforeEach fun beforeEach() {
        every { binder.service } returns service
        every { service.player } returns player
        model = PrefsModel(prefs)
        model.onServiceConnected(null, binder)
    }
    
    @Test fun should_set_player_audio_balance() {
        model.audioBalance = 54
        verify { player.balance = 54 }
    }

    @Test fun should_save_audio_balance_to_prefs() {
        model.audioBalance = 63
        verify { prefs.audioBalance = 63 }
    }
}