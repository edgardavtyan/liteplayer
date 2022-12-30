package com.example.musicplayer.ui.nowplaying

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    init {
        model.onIsPlayingChangedListener = { onIsPlayingChanged(it) }
        model.onDataLoaded = {
            view.setTrackTitle(model.title.toString())
            view.setTrackInfo(model.info.toString())
            view.setCoverArt(model.coverArt)
            onIsPlayingChanged(model.isPlaying)
        }
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
}
