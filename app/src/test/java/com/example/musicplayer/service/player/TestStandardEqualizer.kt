package com.example.musicplayer.service.player

import android.media.audiofx.Equalizer
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

    @Test fun should_set_band_gain_reversed() {
        every { innerEq.numberOfBands } returns 5
        eq.setBandGain(1, 8)
        verify { innerEq.setBandLevel(3, 800) }
    }

    @Test fun should_return_band_gains_reversed() {
        every { innerEq.numberOfBands } returns 5
        every { innerEq.getBandLevel(0) } returns 500
        every { innerEq.getBandLevel(3) } returns 300
        assertEquals(5, eq.getBandGain(4))
        assertEquals(3, eq.getBandGain(1))
    }

    @Test fun should_save_bands_to_prefs() {
        every { innerEq.numberOfBands } returns 5
        every { innerEq.getBandLevel(3) } returns 800
        eq.setBandGain(3, 8)
        verify { prefs.standardEqBands = arrayOf(0, 0, 0, 8, 0) }
    }

    @Test fun should_get_bands_from_prefs() {
        every { prefs.standardEqBands } returns arrayOf(1, 2, 3, 4, 5)
        StandardEqualizer(innerEq, prefs)
        verify { innerEq.setBandLevel(0, 100) }
        verify { innerEq.setBandLevel(1, 200) }
        verify { innerEq.setBandLevel(2, 300) }
        verify { innerEq.setBandLevel(3, 400) }
        verify { innerEq.setBandLevel(4, 500) }
    }

    @Test fun should_return_frequencies_reversed() {
        every { innerEq.numberOfBands } returns 5
        every { innerEq.getCenterFreq(0) } returns 1_000_000
        every { innerEq.getCenterFreq(3) } returns 3_500_000
        assertEquals(1000, eq.getBandFreq(4))
        assertEquals(3500, eq.getBandFreq(1))

    }
}