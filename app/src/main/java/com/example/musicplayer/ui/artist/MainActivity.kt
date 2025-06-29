package com.example.musicplayer.ui.artist

import android.content.Intent
import android.os.Bundle
import android.system.Os.close
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.service.PlayerService
import com.example.musicplayer.ui.nowplaying.NowPlayingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.jvm.java

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject lateinit var adapter: MainAdapter
    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var model: MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, PlayerService::class.java))

        val list: RecyclerView = findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        model.bind()
        presenter.onCreate()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    fun update(items: List<String>, title: String) {
        adapter.update(items, title)
    }

    fun gotoNowPlayingActivity() {
        val intent = Intent(this, NowPlayingActivity::class.java)
        startActivity(intent)
    }

    fun closeApp() {
        finish()
    }
}