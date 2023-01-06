package com.example.musicplayer.ui.track

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.db.Track
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrackActivity : FragmentActivity() {
    companion object {
        const val EXTRA_ALBUM = "extra_album"
        const val EXTRA_ALBUM_TITLE = "extra_album_title"
    }

    @Inject lateinit var adapter: TrackAdapter
    @Inject lateinit var presenter: TrackPresenter

    var albumTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        albumTitle = intent.getStringExtra(EXTRA_ALBUM_TITLE)!!

        presenter.onCreate()

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun updateListData(tracks: List<Track>) {
        adapter.update(tracks)
    }

    fun gotoNowPlayingActivity() {
        val intent = Intent(this, NowPlayingActivity::class.java)
        startActivity(intent)
    }
}