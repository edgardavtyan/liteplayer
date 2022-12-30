package com.example.musicplayer.ui.eq

import android.widget.TextView
import com.example.musicplayer.databinding.ViewEqBandBinding
import com.example.musicplayer.lib.CustomSeekBar
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class TestEqBandView {
    @MockK lateinit var binding: ViewEqBandBinding
    @MockK lateinit var band: CustomSeekBar
    @MockK lateinit var freq: TextView
    @MockK lateinit var gain: TextView

    private lateinit var eqBandView: EqBandView
    private lateinit var onProgressChangedListener: (Int) -> Unit

    @BeforeAll fun beforeAll() {
        setField(binding, "band", band)
        setField(binding, "freq", freq)
        setField(binding, "gain", gain)

        val slot = slot<(Int) -> Unit>()
        every { band.onProgressChangedListener = capture(slot) } answers {}

        mockkStatic(ViewEqBandBinding::class)
        every { ViewEqBandBinding.inflate(any(), any(), any()) } returns binding
        eqBandView = EqBandView(mockk(), null)

        onProgressChangedListener = slot.captured

        every { band.max } returns 30
    }

    @AfterAll fun afterAll() {
        unmockkStatic(ViewEqBandBinding::class)
    }

    @Test fun should_set_positive_gain_text_on_progress_change() {
        onProgressChangedListener(20)
        verify { gain.text = "+5 dB" }
    }

    @Test fun should_set_negative_gain_text_on_progress_change() {
        onProgressChangedListener(3)
        verify { gain.text = "-12 dB" }
    }

    @Test fun should_return_max_gain() {
        assertEquals(15, eqBandView.maxGain)
    }

    @Test fun should_set_max_gain() {
        eqBandView.maxGain = 10
        verify { band.max = 20 }
    }

    @Test fun should_set_positive_gain() {
        eqBandView.setGain(5)
        verify { band.progress = 20 }
        verify { gain.text = "+5 dB" }
    }

    @Test fun should_set_negative_gain() {
        eqBandView.setGain(-3)
        verify { band.progress = 12 }
        verify { gain.text = "-3 dB" }
    }

    @Test fun should_set_frequency_above_thousand() {
        eqBandView.setFreq(3000)
        verify { freq.text = "3 kHz" }
    }

    @Test fun should_set_frequency_below_thousand() {
        eqBandView.setFreq(600)
        verify { freq.text = "600 Hz" }
    }

    private fun setField(clazz: Any, name: String, field: Any) {
        val c = ViewEqBandBinding::class.java
        val f = c.getDeclaredField(name)
        f.isAccessible = true
        f.set(clazz, field)
    }
}