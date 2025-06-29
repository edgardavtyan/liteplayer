package com.example.musicplayer.db

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore


class ArtistDB(context: Context) {
    val KEY_ID = MediaStore.Audio.Artists._ID
    val KEY_TITLE = MediaStore.Audio.Artists.ARTIST
    val KEY_ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
    val KEY_TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS

    val INDEX_ID = 0
    val INDEX_TITLE = 1
    val INDEX_ALBUMS_COUNT = 2
    val INDEX_TRACKS_COUNT = 3

    val URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
    val PROJECTION = arrayOf(KEY_ID, KEY_TITLE, KEY_ALBUMS_COUNT, KEY_TRACKS_COUNT)

    private val resolver = context.contentResolver

    fun getAllArtists(): List<Artist> {
        return getArtistsFromCursor(null, null).sortedBy { it.title }
    }

    fun getArtistFromCursor(cursor: Cursor): Artist {
        val artist = Artist()
        artist.id = cursor.getInt(INDEX_ID)
        artist.title = cursor.getString(INDEX_TITLE)
        artist.albumsCount = cursor.getInt(INDEX_ALBUMS_COUNT)
        artist.tracksCount = cursor.getInt(INDEX_TRACKS_COUNT)
        return artist
    }

    fun getArtistsFromCursor(selection: String?, args: Array<String>?): ArrayList<Artist> {
        val artists = ArrayList<Artist>()
        val cursor = resolver.query(URI, PROJECTION, selection, args, null)!!

        while (cursor.moveToNext()) {
            artists.add(getArtistFromCursor(cursor))
        }

        cursor.close()

        return artists
    }
}