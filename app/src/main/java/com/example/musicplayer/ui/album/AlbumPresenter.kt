package com.example.musicplayer.ui.album

class AlbumPresenter(private val view: AlbumActivity, private val model: AlbumModel) {
    fun onItemClick(position: Int) {
        view.gotoTrackActivity(model.getAlbumIdAt(position))
    }
}