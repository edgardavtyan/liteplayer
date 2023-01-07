package com.example.musicplayer.ui.eq

import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestEqModel {
    @MockK lateinit var eq: StandardEqualizer
    @MockK lateinit var virtualizer: StandardVirtualizer
    @MockK lateinit var service: PlayerService

    private lateinit var model: EqModel

    @BeforeEach fun beforeEach() {
        val binder = mockk<PlayerServiceBinder>()
        every { binder.service } returns service
        every { service.eq } returns eq
        every { service.virtualizer } returns virtualizer
        model = EqModel(mockk())
        model.onServiceConnected(null, binder)
    }

    @Test fun should_set_eq_band_gain() {
        model.setEQBandGain(2, 5)
        verify { eq.setBandGain(2, 5) }
    }

    @Test fun should_return_eq_gains() {
        every { eq.bandCount } returns 5
        every { eq.getBandGain(0) } returns 3
        every { eq.getBandGain(3) } returns 5
        assertArrayEquals(arrayOf(3, 0, 0, 5, 0), model.eqGains)
    }

    @Test fun should_return_eq_freqs() {
        every { eq.bandCount } returns 5
        every { eq.getBandFreq(0) } returns 3000
        every { eq.getBandFreq(3) } returns 12000
        assertArrayEquals(arrayOf(3000, 0, 0, 12000, 0), model.eqFreqs)
    }

    @Test fun should_return_eq_max_gain() {
        every { eq.maxGain } returns 15
        assertEquals(15, model.eqMaxGain)
    }

    @Test fun should_return_virtualizer_max_strength() {
        every { virtualizer.maxStrength } returns 100
        assertEquals(100, model.virtualizerMaxStrength)
    }

    @Test fun should_set_virtualizer_strength() {
        model.virtualizerStrength = 53
        verify { virtualizer.strength = 53 }
    }

    @Test fun should_return_virtualizer_strength() {
        every { virtualizer.strength } returns 42
        assertEquals(42, model.virtualizerStrength)
    }
}