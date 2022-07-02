package com.example.musicplayer

class MainPresenter(private val model: MainModel, private val view: MainActivity) {
    fun onItemClick(position: Int) {
        view.gotoAlbumActivity(model.artists[position].title)
    }
}