package com.example.musicplayer.track

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class TrackActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ALBUM = "extra_album"
    }

    private lateinit var model: TrackModel
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        val album = intent.getIntExtra(EXTRA_ALBUM, -1)
        model = TrackModel(this)
        adapter = TrackAdapter(this, model.getAlbumTracks(album))

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }
}