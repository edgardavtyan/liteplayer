package com.example.musicplayer.db

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore

class TrackDB(context: Context) {
	private val INDEX_ID = 0
	private val INDEX_TRACK = 1
	private val INDEX_TITLE = 2
	private val INDEX_DURATION = 3
	private val INDEX_PATH = 4
	private val INDEX_ALBUM_ID = 5
	private val INDEX_ALBUM = 6
	private val INDEX_ARTIST_ID = 7
	private val INDEX_ARTIST = 8

	private val KEY_ID = MediaStore.Audio.Media._ID
	private val KEY_TRACK = MediaStore.Audio.Media.TRACK
	private val KEY_TITLE = MediaStore.Audio.Media.TITLE
	private val KEY_DURATION = MediaStore.Audio.Media.DURATION
	private val KEY_PATH = MediaStore.Audio.Media.DATA
	private val KEY_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID
	private val KEY_ALBUM = MediaStore.Audio.Media.ALBUM
	private val KEY_ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID
	private val KEY_ARTIST = MediaStore.Audio.Media.ARTIST

	private val URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
	private val PROJECTION = arrayOf(
		KEY_ID,
		KEY_TRACK,
		KEY_TITLE,
		KEY_DURATION,
		KEY_PATH,
		KEY_ALBUM_ID,
		KEY_ALBUM,
		KEY_ARTIST_ID,
		KEY_ARTIST)

	private val resolver = context.contentResolver

	fun getTracksWithAlbumId(albumId: Int): List<Track> {
		val selection = "$KEY_ALBUM_ID=?"
		val args = arrayOf(albumId.toString())
		return getListOfTracks(selection, args)
	}

	private fun getTrackFromCursor(cursor: Cursor): Track {
		val track = Track()
		track.id = cursor.getInt(INDEX_ID)
		track.track = cursor.getInt(INDEX_TRACK)
		track.title = cursor.getString(INDEX_TITLE)
		track.duration = cursor.getLong(INDEX_DURATION)
		track.albumId = cursor.getInt(INDEX_ALBUM_ID)
		track.albumTitle = cursor.getString(INDEX_ALBUM)
		track.artistId = cursor.getInt(INDEX_ARTIST_ID)
		track.artistTitle = cursor.getString(INDEX_ARTIST)
		track.path = cursor.getString(INDEX_PATH)
		return track
	}

	private fun getListOfTracks(selection: String, args: Array<String>): List<Track> {
		val tracks: MutableList<Track> = ArrayList()
		val cursor = resolver.query(URI, PROJECTION, selection, args, KEY_TRACK)!!
		while (cursor.moveToNext()) {
			tracks.add(getTrackFromCursor(cursor))
		}
		cursor.close()
		return tracks
	}
}