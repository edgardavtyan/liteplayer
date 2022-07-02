package com.example.musicplayer.ui.album

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.ui.track.TrackActivity

class AlbumActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTIST = "extra_artist"
    }

    private lateinit var model: AlbumModel
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        model = AlbumModel(this, intent.getStringExtra(EXTRA_ARTIST)!!)
        adapter = AlbumAdapter(this, model, AlbumPresenter(this, model))

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    fun gotoTrackActivity(albumId: Int) {
        val intent = Intent(this, TrackActivity::class.java)
        intent.putExtra(TrackActivity.EXTRA_ALBUM, albumId)
        startActivity(intent)
    }
}