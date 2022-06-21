package com.example.musicplayer

class MainPresenter(val model: MainModel, val view: MainActivity) {
    fun onItemClick(position: Int) {
        view.gotoAlbumActivity(model.artists[position].title)
    }
}