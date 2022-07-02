package com.example.musicplayer.ui.track

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.App
import com.example.musicplayer.R
import com.example.musicplayer.db.Track
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity
import javax.inject.Inject

class TrackActivity : Activity() {
    companion object {
        const val EXTRA_ALBUM = "extra_album"
    }

    @Inject lateinit var adapter: TrackAdapter
    @Inject lateinit var presenter: TrackPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        DaggerTrackComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .trackModule(TrackModule(this))
            .build()
            .inject(this)

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