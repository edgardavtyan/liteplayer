package com.example.musicplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.album.AlbumActivity


class MainActivity : AppCompatActivity() {
    private lateinit var model: MainModel
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = MainModel(this)
        presenter = MainPresenter(model ,this)
        adapter = MainAdapter(this, presenter, model.artists)

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    fun gotoAlbumActivity(artist: String) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra(AlbumActivity.EXTRA_ARTIST, artist)
        startActivity(intent)
    }
}