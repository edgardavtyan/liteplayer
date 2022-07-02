package com.example.musicplayer.ui.artist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.App
import com.example.musicplayer.DaggerMainComponent
import com.example.musicplayer.R
import com.example.musicplayer.ui.album.AlbumActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
            .appDaggerComponent((application as App).appComponent)
            .mainDaggerModule(MainDaggerModule(this))
            .build()
            .inject(this)

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