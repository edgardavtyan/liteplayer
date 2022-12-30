package com.example.musicplayer.ui.eq

import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.service.PlayerServiceBinder
import com.example.musicplayer.service.player.StandardEqualizer
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
    @MockK lateinit var service: PlayerService

    private lateinit var model: EqModel

    @BeforeEach fun beforeEach() {
        val binder = mockk<PlayerServiceBinder>()
        every { binder.service } returns service
        every { service.eq } returns eq
        model = EqModel(mockk())
        model.onServiceConnected(null, binder)
    }

    @Test fun should_set_band_gain() {
        model.setBandGain(2, 5)
        verify { eq.setBandGain(2, 5) }
    }

    @Test fun should_return_gains() {
        every { eq.bandCount } returns 5
        every { eq.getBandGain(0) } returns 3
        every { eq.getBandGain(3) } returns 5
        assertArrayEquals(arrayOf(3, 0, 0, 5, 0), model.gains)
    }

    @Test fun should_return_freqs() {
        every { eq.bandCount } returns 5
        every { eq.getBandFreq(0) } returns 3000
        every { eq.getBandFreq(3) } returns 12000
        assertArrayEquals(arrayOf(3000, 0, 0, 12000, 0), model.freqs)
    }

    @Test fun should_return_max_gain() {
        every { eq.maxGain } returns 15
        assertEquals(15, model.maxGain)
    }
}