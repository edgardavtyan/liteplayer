package com.example.musicplayer.ui.album

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.db.Album
import com.example.musicplayer.ui.track.TrackActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumActivity : FragmentActivity() {
    companion object {
        const val EXTRA_ARTIST = "extra_artist"
    }

    @Inject lateinit var adapter: AlbumAdapter
    @Inject lateinit var presenter: AlbumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        presenter.onCreate()

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    fun gotoTrackActivity(albumId: Int, albumTitle: String) {
        val intent = Intent(this, TrackActivity::class.java)
        intent.putExtra(TrackActivity.EXTRA_ALBUM, albumId)
        intent.putExtra(TrackActivity.EXTRA_ALBUM_TITLE, albumTitle)
        startActivity(intent)
    }

    fun updateData(albums: List<Album>) {
        adapter.update(albums)
    }
}