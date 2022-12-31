package com.example.musicplayer.service.player

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

    private lateinit var virtualizer: StandardVirtualizer

    @BeforeAll fun beforeAll() {
        virtualizer = StandardVirtualizer(baseVirtualizer)
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
}