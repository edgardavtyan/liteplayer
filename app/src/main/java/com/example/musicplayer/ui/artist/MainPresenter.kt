package com.example.musicplayer.ui.artist

class MainPresenter(private val model: MainModel, private val view: MainActivity) {
    fun onItemClick(position: Int) {
        val artist = model.artists[position].title
        if (model.albumCount(artist) == 1) {
            val album = model.getFirstAlbum(artist)
            view.gotoTrackActivity(album.id, album.title)
        } else {
            view.gotoAlbumActivity(model.artists[position].title)
        }
    }

    fun onCreate() {
        view.update(model.artists)
    }
}