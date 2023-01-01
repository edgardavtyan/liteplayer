package com.example.musicplayer.ui.eq

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestEqPresenter {
    @MockK lateinit var view: EqActivity
    @MockK lateinit var model: EqModel

    private lateinit var presenter: EqPresenter

    @BeforeEach fun beforeEach() {
        presenter = EqPresenter(view, model)
    }

    @Test fun should_bind_model() {
        presenter.onCreate()
        verify { model.bind() }
    }

    @Test fun should_unbind_model() {
        presenter.onDestroy()
        verify { model.unbind() }
    }

    @Test fun should_set_eq_gains_on_data_loaded() {
        every { model.eqGains } returns arrayOf(1, 2, 3, 4, 5)
        invokeOnDataConnected()
        verify { view.setEQBandGain(0, 1) }
        verify { view.setEQBandGain(1, 2) }
        verify { view.setEQBandGain(2, 3) }
        verify { view.setEQBandGain(3, 4) }
        verify { view.setEQBandGain(4, 5) }
    }

    @Test fun should_set_eq_frequencies_on_data_loaded() {
        every { model.eqFreqs } returns arrayOf(100, 500, 2000, 5000, 10000)
        invokeOnDataConnected()
        verify { view.setEQBandFreq(0, 100) }
        verify { view.setEQBandFreq(1, 500) }
        verify { view.setEQBandFreq(2, 2000) }
        verify { view.setEQBandFreq(3, 5000) }
        verify { view.setEQBandFreq(4, 10000) }
    }

    @Test fun should_set_eq_band_count_on_data_loaded() {
        every { model.eqGains } returns arrayOf(100, 500, 2000, 5000, 10000)
        invokeOnDataConnected()
        verify { view.setEQBandCount(5) }
    }

    @Test fun should_set_band_max_gain_on_service_connected() {
        every { model.eqMaxGain } returns 15
        invokeOnDataConnected()
        verify { view.setEQMaxGain(15) }
    }

    @Test fun should_update_eq_gain() {
        presenter.onEQBandGainChanged(2, 5)
        verify { model.setEQBandGain(2, 5) }
    }

    @Test fun should_set_virtualizer_max_strength() {
        every { model.virtualizerMaxStrength } returns 100
        invokeOnDataConnected()
        verify { view.setVirtualizerMaxStrength(100) }
    }

    @Test fun should_set_virtualizer_strength() {
        presenter.onVirtualizerStrengthChanged(75)
        verify { model.virtualizerStrength = 75 }
    }

    @Test fun should_get_virtualizer_strength_from_model() {
        every { model.virtualizerStrength } returns 65
        invokeOnDataConnected()
        verify { view.setVirtualizerStrength(65) }
    }

    private fun invokeOnDataConnected() {
        val slot = slot<() -> Unit>()
        every { model.onDataLoaded = capture(slot) } answers {}
        presenter.onCreate()
        slot.captured()
    }
}