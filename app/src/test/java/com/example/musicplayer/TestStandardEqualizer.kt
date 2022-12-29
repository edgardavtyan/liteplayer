package com.example.musicplayer

import android.media.audiofx.Equalizer
import com.example.musicplayer.service.player.StandardEqualizer
import com.example.musicplayer.ui.prefs.Prefs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestStandardEqualizer {
    @MockK lateinit var innerEq: Equalizer
    @MockK lateinit var prefs: Prefs

    private lateinit var eq: StandardEqualizer

    @BeforeEach fun beforeEach() {
        eq = StandardEqualizer(innerEq, prefs)
    }

    @Test fun should_return_min_max_gain() {
        every { innerEq.bandLevelRange } returns shortArrayOf(-1500, 1500)
        assertEquals(-15, eq.minGain)
        assertEquals(15, eq.maxGain)
    }

    @Test fun should_return_band_count() {
        every { innerEq.numberOfBands } returns 5
        assertEquals(5, eq.bandCount)
    }

    @Test fun should_set_band_gain() {
        eq.setBandGain(3, 8)
        verify { innerEq.setBandLevel(3, 800) }
    }

    @Test fun should_return_band_gains() {
        every { innerEq.numberOfBands } returns 5
        every { innerEq.getBandLevel(0) } returns 500
        every { innerEq.getBandLevel(2) } returns 300
        assertEquals(5, eq.getBandGain(0))
        assertEquals(3, eq.getBandGain(2))
    }

    @Test fun should_save_bands_to_prefs() {
        every { innerEq.numberOfBands } returns 5
        every { innerEq.getBandLevel(3) } returns 800
        eq.setBandGain(3, 8)
        verify { prefs.standardEqBands = arrayOf(0, 0, 0, 8, 0) }
    }

    @Test fun should_get_bands_from_prefs() {
        every { prefs.standardEqBands } returns arrayOf(1, 2, 3, 4, 5)
        val newEq = StandardEqualizer(innerEq, prefs)
        verify { newEq.setBandGain(0, 1) }
        verify { newEq.setBandGain(1, 2) }
        verify { newEq.setBandGain(2, 3) }
        verify { newEq.setBandGain(3, 4) }
        verify { newEq.setBandGain(4, 5) }
    }

    @Test fun should_return_frequencies() {
        every { innerEq.getCenterFreq(0) } returns 1000
        every { innerEq.getCenterFreq(3) } returns 3500
        assertEquals(1000, eq.getBandFreq(0))
        assertEquals(3500, eq.getBandFreq(3))

    }
}