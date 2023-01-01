package com.example.musicplayer.ui.nowplaying_bar

class NowPlayingBarPresenter(
    private val view: NowPlayingBarFragment,
    private val model: NowPlayingBarModel)
{
    private var isDataLoaded: Boolean = false

    fun onCreateView() {
        model.bind()
        model.onDataLoaded = {
            isDataLoaded = true
            onResume()
        }
    }

    fun onResume() {
        if (!isDataLoaded) {
            return
        }

        if (model.hasTrack) {
            view.setTitle(model.nowPlayingTitle)
            view.setCover(model.cover)
            view.setIsPlaying(model.isPlaying)
        } else {
            view.setTitle("LitePlayer")
            view.setIsPlaying(false)
        }
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onBtnPlayPauseClicked() {
        model.playPause()
        view.setIsPlaying(model.isPlaying)
    }

    fun onBtnSettingsClicked() {
        view.gotoPrefs()
    }

    fun onBtnEqClicked() {
        view.gotoEq()
    }

    fun onBarClicked() {
        view.gotoNowPlaying()
    }
}
