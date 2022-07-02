package com.example.musicplayer.ui.track

class TrackPresenter(private val view: TrackActivity, private val model: TrackModel) {
    fun onCreate() {
        model.bind()
        view.updateListData(model.tracks)
    }

    fun onDestroy() {
        model.unbind()
    }

    fun onItemClick(position: Int) {
        model.playTrack(position)
        view.gotoNowPlayingActivity()
    }
}