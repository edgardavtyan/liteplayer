package com.example.musicplayer.ui.artist

import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val model: MainModel,
    private val view: MainActivity)
{
    fun onItemClick(position: Int) {
        val artist = model.artists[position].title
        if (model.albumCount(artist) == 1) {
            view.gotoTrackActivity(model.getFirstAlbumId(artist))
        } else {
            view.gotoAlbumActivity(model.artists[position].title)
        }
    }

    fun onCreate() {
        view.update(model.artists)
    }
}