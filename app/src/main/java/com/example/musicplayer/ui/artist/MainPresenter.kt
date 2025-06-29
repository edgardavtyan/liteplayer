package com.example.musicplayer.ui.artist

class MainPresenter(private val model: MainModel, private val view: MainActivity) {
    enum class Mode {
        ARTISTS, ALBUMS, TRACKS, SINGLE_ALBUM
    }

    private var mode = Mode.ARTISTS

    fun onCreate() {
        view.update(model.artists.map { it.title }, "Artists")
    }

    fun onItemClick(position: Int) {
        if (mode == Mode.ARTISTS) {
            val artist = model.artists[position].title
            if (model.albumCount(artist) == 1) {
                val album = model.getFirstAlbum(artist)
                val tracks = model.loadTracks(album.id)
                view.update(tracks.map { it.title }, album.title)
                mode = Mode.SINGLE_ALBUM
            }
            else {
                val albums = model.loadAlbums(artist)
                view.update(albums.map { it.title }, artist)
                mode = Mode.ALBUMS
            }
        }
        else if (mode == Mode.ALBUMS) {
            val album = model.getAlbumAt(position)
            val tracks = model.loadTracks(album.id)
            view.update(tracks.map { it. title }, album.title)
            mode = Mode.TRACKS
        }
        else if (mode == Mode.TRACKS || mode == Mode.SINGLE_ALBUM) {
            model.playTrack(position)
            view.gotoNowPlayingActivity()
        }
    }

    fun onBackPressed() {
        when (mode) {
            Mode.SINGLE_ALBUM, Mode.ALBUMS -> {
                view.update(model.artists.map { it.title }, "Artists")
                mode = Mode.ARTISTS
            }
            Mode.TRACKS -> {
                val albums = model.loadAlbums(model.artist!!)
                view.update(albums.map { it.title }, model.artist!!)
                mode = Mode.ALBUMS
            }
            else -> {
                view.closeApp()
            }
        }
    }
}