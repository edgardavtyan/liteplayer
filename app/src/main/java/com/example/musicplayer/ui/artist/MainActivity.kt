package com.example.musicplayer.ui.artist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.db.Artist
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.ui.album.AlbumActivity
import com.example.musicplayer.ui.track.TrackActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject lateinit var adapter: MainAdapter
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, PlayerService::class.java))

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        presenter.onCreate()
    }

    fun update(newArtists: List<Artist>) {
        adapter.update(newArtists)
    }

    fun gotoAlbumActivity(artist: String) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra(AlbumActivity.EXTRA_ARTIST, artist)
        startActivity(intent)
    }

    fun gotoTrackActivity(albumId: Int, albumTitle: String) {
        val intent = Intent(this, TrackActivity::class.java)
        intent.putExtra(TrackActivity.EXTRA_ALBUM_TITLE, albumTitle)
        intent.putExtra(TrackActivity.EXTRA_ALBUM, albumId)
        startActivity(intent)
    }
}