package com.example.musicplayer.service.player

import android.content.SharedPreferences
import android.media.audiofx.Virtualizer
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestStandardVirtualizer {
    @MockK lateinit var baseVirtualizer: Virtualizer
    @MockK lateinit var prefs: SharedPreferences

    private lateinit var virtualizer: StandardVirtualizer

    @BeforeAll fun beforeAll() {
        virtualizer = StandardVirtualizer(baseVirtualizer, prefs)
    }

    @Test fun should_be_enabled() {
        verify { baseVirtualizer.enabled = true }
    }

    @Test fun should_set_strength() {
        virtualizer.strength = 75
        verify { baseVirtualizer.setStrength(750) }
    }

    @Test fun should_return_strength() {
        every { baseVirtualizer.roundedStrength } returns 420
        assertEquals(42, virtualizer.strength)
    }

    @Test fun should_set_strength_from_prefs() {
        every { prefs.getInt("virtualizer-strength", 0) } returns 28
        StandardVirtualizer(baseVirtualizer, prefs)
        verify { baseVirtualizer.setStrength(280) }
    }

    @Test fun should_save_strength_to_prefs() {
        virtualizer.strength = 33
        verify { prefs.edit().putInt("virtualizer-strength", 33).apply() }
    }
}