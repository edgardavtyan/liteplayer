package com.example.musicplayer.service.player

import com.example.musicplayer.db.Track
import com.un4seen.bass.BASS

class BassPlayer: Player {
    init {
        BASS.BASS_Init(-1, 44100, 0)
    }

    override var sessionId: Int = 0

    override val isPlaying: Boolean get() = BASS.BASS_ChannelIsActive(sessionId) == BASS.BASS_ACTIVE_PLAYING

    override var track: Track? = null

    private val onIsPlayingChangedListeners = ArrayList<(Boolean) -> Unit>()

    private val onPreparedListeners = ArrayList<() -> Unit>()

    override fun addOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.add(listener)
    }

    override fun removeOnIsPlayingChangedListener(listener: (Boolean) -> Unit) {
        onIsPlayingChangedListeners.remove(listener)
    }

    override fun addOnPreparedListener(listener: () -> Unit) {
        onPreparedListeners.add(listener)
    }

    override fun removeOnPreparedListener(listener: () -> Unit) {
        onPreparedListeners.remove(listener)
    }

    override fun setBalance(balance: Int) {
        BASS.BASS_ChannelSetAttribute(sessionId, BASS.BASS_ATTRIB_PAN, balance / 100f)
    }

    override fun playPause() {
        when(BASS.BASS_ChannelIsActive(sessionId)) {
            BASS.BASS_ACTIVE_PLAYING -> BASS.BASS_ChannelPause(sessionId)
            BASS.BASS_ACTIVE_PAUSED -> BASS.BASS_ChannelStart(sessionId)
        }

        onIsPlayingChangedListeners.forEach { it(isPlaying) }
    }

    override fun pause() {
        BASS.BASS_ChannelPause(sessionId)
        onIsPlayingChangedListeners.forEach { it(false) }
    }

    override fun playTrack(track: Track) {
        this.track = track
        BASS.BASS_ChannelFree(sessionId)
        sessionId = BASS.BASS_StreamCreateFile(track.path, 0, 0, BASS.BASS_SAMPLE_LOOP)
        BASS.BASS_ChannelPlay(sessionId, false)
        onPreparedListeners.forEach { it() }
    }
}