package com.example.musicplayer.ui.album

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.App
import com.example.musicplayer.R
import com.example.musicplayer.db.Album
import com.example.musicplayer.ui.track.TrackActivity
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTIST = "extra_artist"
    }

    @Inject lateinit var adapter: AlbumAdapter
    @Inject lateinit var presenter: AlbumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        DaggerAlbumComponent
            .builder()
            .appDaggerComponent((application as App).appComponent)
            .albumDaggerModule(AlbumDaggerModule(this))
            .build()
            .inject(this)

        presenter.onCreate()

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    fun gotoTrackActivity(albumId: Int) {
        val intent = Intent(this, TrackActivity::class.java)
        intent.putExtra(TrackActivity.EXTRA_ALBUM, albumId)
        startActivity(intent)
    }

    fun updateData(albums: List<Album>) {
        adapter.update(albums)
    }
}