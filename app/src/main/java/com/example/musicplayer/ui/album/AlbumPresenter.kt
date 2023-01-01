package com.example.musicplayer.ui.album

class AlbumPresenter(private val view: AlbumActivity, private val model: AlbumModel) {
    fun onCreate() {
        view.updateData(model.albums)
    }

    fun onItemClick(position: Int) {
        view.gotoTrackActivity(model.albums[position].id)
    }
}