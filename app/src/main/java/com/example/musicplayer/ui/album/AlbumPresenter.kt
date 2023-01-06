package com.example.musicplayer.ui.album

class AlbumPresenter(private val view: AlbumActivity, private val model: AlbumModel) {
    val title = model.artist

    fun onCreate() {
        view.updateData(model.albums)
    }

    fun onItemClick(position: Int) {
        view.gotoTrackActivity(model.albums[position].id, model.albums[position].title)
    }
}