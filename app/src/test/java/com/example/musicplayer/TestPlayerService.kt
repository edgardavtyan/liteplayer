package com.example.musicplayer

import com.example.musicplayer.service.player.PlayerAudioManager
import com.example.musicplayer.service.player.StandardPlayer
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.ui.prefs.Prefs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestPlayerService {
    @MockK lateinit var prefs: Prefs
    @MockK lateinit var player: StandardPlayer
    @MockK lateinit var audioManager: PlayerAudioManager

    private lateinit var service: PlayerService

    @BeforeEach fun beforeEach() {
        service = PlayerService()
        service.prefs = prefs
        service.audioManager = audioManager
        service.player = player
    }

    @Test fun should_set_balance_on_pref_change() {
        val listenerSlot = slot<(Int) -> Unit>()
        every { prefs.onAudioBalanceChangeListener = capture(listenerSlot) } answers {}

        service.onStartCommand(null, 0, 0)
        listenerSlot.captured(63)

        verify { player.balance = 63 }
    }

    @Test fun should_pause_player_on_focus_loss() {
        val listenerSlot = slot<() -> Unit>()
        every { audioManager.onFocusLossListener = capture(listenerSlot) } answers {}

        service.onStartCommand(null, 0, 0)
        listenerSlot.captured()

        verify { player.pause() }
    }
}