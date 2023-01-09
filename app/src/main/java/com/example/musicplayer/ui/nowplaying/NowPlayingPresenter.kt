package com.example.musicplayer.ui.nowplaying

import com.example.musicplayer.lib.Timer

class NowPlayingPresenter(
    private val view: NowPlayingActivity,
    private val model: NowPlayingModel) {

    val seekTimer = Timer(200) { view.setSeek(model.seek) }

    private val onIsPlayingChangedListener: (Boolean) -> Unit = { onIsPlayingChanged(it) }

    fun onCreate() {
        model.bind()
        model.onDataLoaded = {
            model.addOnIsPlayingChangedListener(onIsPlayingChangedListener)
            view.setTrackTitle(model.title.toString())
            view.setTrackInfo(model.info.toString())
            view.setCoverArt(model.cover)
            view.setSeekMax(model.seekMax ?: 0)
            seekTimer.run()
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

    fun onSeekChanged(seek: Int) {
        model.seek = seek
    }
}
