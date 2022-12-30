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
        every { model.gains } returns arrayOf(1, 2, 3, 4, 5)
        invokeOnDataConnected()
        verify { view.setBandGain(0, 1) }
        verify { view.setBandGain(1, 2) }
        verify { view.setBandGain(2, 3) }
        verify { view.setBandGain(3, 4) }
        verify { view.setBandGain(4, 5) }
    }

    @Test fun should_set_eq_frequencies_on_data_loaded() {
        every { model.freqs } returns arrayOf(100, 500, 2000, 5000, 10000)
        invokeOnDataConnected()
        verify { view.setBandFreq(0, 100) }
        verify { view.setBandFreq(1, 500) }
        verify { view.setBandFreq(2, 2000) }
        verify { view.setBandFreq(3, 5000) }
        verify { view.setBandFreq(4, 10000) }
    }

    @Test fun should_set_eq_band_count_on_data_loaded() {
        every { model.gains } returns arrayOf(100, 500, 2000, 5000, 10000)
        invokeOnDataConnected()
        verify { view.setBandCount(5) }
    }

    @Test fun should_set_band_max_gain_on_service_connected() {
        every { model.maxGain } returns 15
        invokeOnDataConnected()
        verify { view.setMaxGain(15) }
    }

    @Test fun should_update_eq_gain() {
        presenter.onBandGainChanged(2, 5)
        verify { model.setBandGain(2, 5) }
    }

    private fun invokeOnDataConnected() {
        val slot = slot<() -> Unit>()
        every { model.onDataLoaded = capture(slot) } answers {}
        presenter.onCreate()
        slot.captured()
    }
}