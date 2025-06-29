package com.example.musicplayer.db

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore


class AlbumDB(context: Context) {
    val KEY_ID = MediaStore.Audio.Albums._ID
    val KEY_TITLE = MediaStore.Audio.Albums.ALBUM
    val KEY_ARTIST_TITLE = MediaStore.Audio.Albums.ARTIST
    val KEY_ART = MediaStore.Audio.Albums.ALBUM_ART
    val KEY_TRACKS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS
    val KEY_DATE = MediaStore.Audio.Albums.FIRST_YEAR

    private val INDEX_ID = 0
    private val INDEX_TITLE = 1
    private val INDEX_ARTIST_TITLE = 2
    private val INDEX_ART = 3
    private val INDEX_TRACKS_COUNT = 4
    private val INDEX_DATE  =5

    private val URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
    private val PROJECTION = arrayOf(
        KEY_ID,
        KEY_TITLE,
        KEY_ARTIST_TITLE,
        KEY_ART,
        KEY_TRACKS_COUNT,
        KEY_DATE
    )

    private val resolver = context.contentResolver

    fun getAlbumsWithArtistTitle(artistTitle: String): List<Album> {
        val selection = "$KEY_ARTIST_TITLE=?"
        val args = arrayOf(artistTitle)
        return getListOfAlbums(selection, args)
    }

    private fun getAlbumFromCursor(cursor: Cursor): Album {
        val album = Album()
        album.id = cursor.getInt(INDEX_ID)
        album.title = cursor.getString(INDEX_TITLE)
        album.artistTitle = cursor.getString(INDEX_ARTIST_TITLE)
        album.art = cursor.getString(INDEX_ART)
        album.tracksCount = cursor.getInt(INDEX_TRACKS_COUNT)
        album.date = cursor.getLong(INDEX_DATE)
        return album
    }

    private fun getListOfAlbums(selection: String?, args: Array<String>?): List<Album> {
        val orderStr = String.format("%s, %s", KEY_DATE, KEY_TITLE)
        val cursor = resolver.query(URI, PROJECTION, selection, args, orderStr)!!

        val albums = ArrayList<Album>()
        while (cursor.moveToNext()) {
            albums.add(getAlbumFromCursor(cursor))
        }

        cursor.close()
        return albums.sortedBy { it.title }.sortedBy { it.date }
    }
}