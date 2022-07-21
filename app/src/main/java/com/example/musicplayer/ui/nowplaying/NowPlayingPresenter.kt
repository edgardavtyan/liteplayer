package com.example.musicplayer.ui.nowplaying

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    init {
        model.onServiceConnectedListener = OnServiceConnectedListener { onServiceConnected() }
        model.onIsPlayingChangedListener = { onIsPlayingChanged(it) }
    }

    fun onCreate() {
        model.bind()
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onPlayPauseClick() {
        model.playPause()
    }

    private fun onIsPlayingChanged(isPlaying: Boolean) {
        if (isPlaying) {
            view.setPlaying()
        } else {
            view.setPaused()
        }
    }

    private fun onServiceConnected() {
        view.trackTitle = model.title.toString()
        view.trackInfo = model.info.toString()
        onIsPlayingChanged(model.isPlaying)
    }
}
