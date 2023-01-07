package com.example.musicplayer.service

import com.example.musicplayer.service.player.PlayerAudioManager
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
    @MockK lateinit var player: StandardPlayer
    @MockK lateinit var audioManager: PlayerAudioManager

    private lateinit var service: PlayerService

    @BeforeEach fun beforeEach() {
        service = PlayerService()
        service.audioManager = audioManager
        service.player = player
    }

    @Test fun should_pause_player_on_focus_loss() {
        val listenerSlot = slot<() -> Unit>()
        every { audioManager.onFocusLossListener = capture(listenerSlot) } answers {}

        service.onStartCommand(null, 0, 0)
        listenerSlot.captured()

        verify { player.pause() }
    }

    @Test fun should_request_focus_on_play_pause() {
        val listenerSlot = slot<(Boolean) -> Unit>()
        every { player.addOnIsPlayingChangedListener(capture(listenerSlot)) } answers {}

        service.onStartCommand(null, 0, 0)

        listenerSlot.captured(true)
        verify { audioManager.setFocused(true) }

        listenerSlot.captured(false)
        verify { audioManager.setFocused(false) }
    }
}