package com.example.musicplayer

import android.media.MediaPlayer
import com.example.musicplayer.player.StandardAudioEngine
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestStandardAudioEngine {
    @MockK lateinit var player: MediaPlayer

    private lateinit var audioEngine: StandardAudioEngine
    private lateinit var onPreparedListener: () -> Unit

    @BeforeEach fun beforeEach() {
        val slot = slot<MediaPlayer.OnPreparedListener>()
        every {
            player.setOnPreparedListener(capture(slot))
        } answers { onPreparedListener = { slot.captured.onPrepared(player) } }

        audioEngine = StandardAudioEngine(player)
    }

    @Test fun should_set_zero_balance() {
        audioEngine.balance = 0
        verify { player.setVolume(1.0f, 1.0f) }
    }

    @Test fun should_set_left_balance() {
        audioEngine.balance = -35
        verify { player.setVolume(1.0f, 0.65f) }
    }

    @Test fun should_set_right_balance() {
        audioEngine.balance = 68
        verify { player.setVolume(0.32f, 1.0f) }
    }

    @Test fun should_return_true_if_playing() {
        every { player.isPlaying } returns true
        assertTrue(audioEngine.isPlaying)
    }

    @Test fun should_return_false_if_not_playing() {
        every { player.isPlaying } returns false
        assertFalse(audioEngine.isPlaying)
    }

    @Test fun should_pause_if_playing() {
        every { player.isPlaying } returns true
        audioEngine.playPause()
        verify { player.pause() }
    }

    @Test fun should_play_if_not_playing() {
        every { player.isPlaying } returns false
        audioEngine.playPause()
        verify { player.start() }
    }

    @Test fun should_pause_via_separate_method() {
        audioEngine.pause()
        verify { player.pause() }
    }

    @Test fun should_play_track_async() {
        audioEngine.playTrack("filename")
        verify {
            player.stop()
            player.reset()
            player.isLooping = true
            player.setDataSource("filename")
            player.prepareAsync()
        }
    }

    @Test fun should_start_after_prepared() {
        onPreparedListener()
        verify { player.start() }
    }

    @Test fun should_call_onPrepared_listener() {
        val listener = mockk<() -> Unit>()
        audioEngine.onPreparedListener = listener
        onPreparedListener()
        verify { listener() }
    }

    @Test fun should_call_isPlaying_changed_listener_on_resume() {
        every { player.isPlaying } returns false
        val listener = mockk<(Boolean) -> Unit>()
        audioEngine.addOnIsPlayingChangedListener(listener)
        audioEngine.playPause()
        verify { listener(false) }
    }

    @Test fun should_call_isPlaying_changed_listener_on_pause() {
        every { player.isPlaying } returns true
        val listener = mockk<(Boolean) -> Unit>()
        audioEngine.addOnIsPlayingChangedListener(listener)
        audioEngine.playPause()
        verify { listener(true) }
    }

    @Test fun should_call_isPlaying_changed_listener_on_separate_pause_method() {
        every { player.isPlaying } returns false
        val listener = mockk<(Boolean) -> Unit>()
        audioEngine.addOnIsPlayingChangedListener(listener)
        audioEngine.pause()
        verify { listener(false) }
    }

    @Test fun should_call_isPlaying_changed_listener_on_prepared() {
        every { player.isPlaying } returns true
        val listener = mockk<(Boolean) -> Unit>()
        audioEngine.addOnIsPlayingChangedListener(listener)
        onPreparedListener()
        verify { listener(true) }
    }

    @Test fun should_remove_isPlaying_changed_listener() {
        every { player.isPlaying } returns true
        val listener = mockk<(Boolean) -> Unit>()
        audioEngine.addOnIsPlayingChangedListener(listener)
        audioEngine.removeOnIsPlayingChangedListener(listener)
        audioEngine.pause()
        verify(exactly = 0) { listener(true) }
    }
}