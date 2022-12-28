package com.example.musicplayer.ui.artist

import com.example.musicplayer.db.Album
import com.example.musicplayer.db.AlbumDB
import com.example.musicplayer.db.Artist
import com.example.musicplayer.db.ArtistDB
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TestMainModel {
    @MockK lateinit var albumDB: AlbumDB
    @MockK lateinit var artistDB: ArtistDB

    private lateinit var model: MainModel

    @BeforeEach fun beforeEach() {
        model = MainModel(artistDB, albumDB)
    }

    @Test fun should_return_album_count() {
        val list = listOf(Album(), Album(), Album())
        every { albumDB.getAlbumsWithArtistTitle("artist") } returns list
        assertEquals(3, model.albumCount("artist"))
    }

    @Test fun should_return_first_album_id() {
        val album = Album()
        album.id = 12345
        every { albumDB.getAlbumsWithArtistTitle("artist") } returns listOf(album)
        assertEquals(12345, model.getFirstAlbumId("artist"))
    }

    @Test fun should_return_all_artists() {
        val artists = listOf(Artist(), Artist(), Artist())
        every { artistDB.getAllArtists() } returns artists
        val newModel = MainModel(artistDB, albumDB)
        assertEquals(artists, newModel.artists)
    }
}