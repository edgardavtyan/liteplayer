package com.example.musicplayer.ui.track

class TrackPresenter(private val view: TrackActivity, private val model: TrackModel) {
    private val onPreparedListener = { view.gotoNowPlayingActivity() }

    fun onCreate() {
        model.bind()
        model.onDataLoaded = {
            model.addOnPreparedListener(onPreparedListener)
        }
        view.updateListData(model.tracks)
    }

    fun onDestroy() {
        model.unbind()
        model.removeOnPreparedListener(onPreparedListener)
    }

    fun onItemClick(position: Int) {
        model.playTrack(position)
    }
}