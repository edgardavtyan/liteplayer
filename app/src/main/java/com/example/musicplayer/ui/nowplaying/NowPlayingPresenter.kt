package com.example.musicplayer.ui.nowplaying

import com.example.musicplayer.lib.Timer

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    private val onIsPlayingChangedListener: (Boolean) -> Unit = { onIsPlayingChanged(it) }

    private val seekTimer = Timer(200) {
        view.setSeekTimeNow(model.position)
    }

    fun onCreate() {
        model.bind()
        model.onDataLoaded = {
            model.addOnIsPlayingChangedListener(onIsPlayingChangedListener)
            view.setTrackTitle(model.title.toString())
            view.setTrackInfo(model.info.toString())
            view.setCoverArt(model.cover)
            view.setSeekTimeTotal(model.duration)
            seekTimer.run()
            onIsPlayingChanged(model.isPlaying)
        }
    }

    fun onDestroy() {
        model.removeOnIsPlayingChangedListener(onIsPlayingChangedListener)
        model.unbind()
        seekTimer.stop()
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

    fun onSeekbarStopTracking(progress: Int) {
        model.position = progress
    }
}
