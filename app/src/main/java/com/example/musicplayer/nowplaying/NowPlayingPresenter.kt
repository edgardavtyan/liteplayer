package com.example.musicplayer.nowplaying

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    init {
        model.onServiceConnectedListener = OnServiceConnectedListener { onServiceConnected() }
    }

    fun onCreate() {
        model.bind()
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onPlayPauseClick() {
        model.playPause()
        if (model.isPlaying) {
            view.setPlaying()
        } else {
            view.setPaused()
        }

    }

    private fun onServiceConnected() {
        view.trackTitle = model.title.toString()
        view.trackInfo = model.info.toString()
        if (model.isPlaying) {
            view.setPlaying()
        } else {
            view.setPaused()
        }
    }
}
