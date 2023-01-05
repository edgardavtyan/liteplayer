package com.example.musicplayer.ui.nowplaying

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    private val onIsPlayingChangedListener: (Boolean) -> Unit = { onIsPlayingChanged(it) }

    fun onCreate() {
        model.bind()
        model.onDataLoaded = {
            model.addOnIsPlayingChangedListener(onIsPlayingChangedListener)
            view.setTrackTitle(model.title.toString())
            view.setTrackInfo(model.info.toString())
            view.setCoverArt(model.cover)
            onIsPlayingChanged(model.isPlaying)
        }
    }

    fun onDestroy() {
        model.removeOnIsPlayingChangedListener(onIsPlayingChangedListener)
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
