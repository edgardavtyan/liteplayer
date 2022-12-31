package com.example.musicplayer.ui.album

import javax.inject.Inject

class AlbumPresenter @Inject constructor(
    private val view: AlbumActivity,
    private val model: AlbumModel)
{
    fun onCreate() {
        view.updateData(model.albums)
    }

    fun onItemClick(position: Int) {
        view.gotoTrackActivity(model.albums[position].id)
    }
}