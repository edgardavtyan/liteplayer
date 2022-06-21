package com.example.musicplayer.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.MainAdapter
import com.example.musicplayer.MainModel
import com.example.musicplayer.R

class AlbumActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTIST = "extra_artist"
    }

    private lateinit var model: AlbumModel
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val artist = intent.getStringExtra(EXTRA_ARTIST)!!
        model = AlbumModel(this)
        adapter = AlbumAdapter(this, model.getArtistAlbums(artist))

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }
}