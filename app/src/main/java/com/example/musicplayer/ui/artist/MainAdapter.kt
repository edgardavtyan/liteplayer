package com.example.musicplayer.ui.artist

import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Artist
import com.example.musicplayer.lib.TitleAdapter

class MainAdapter(private val presenter: MainPresenter)
    : TitleAdapter<MainViewHolder, Artist>() {

    override val layout = R.layout.listitem_artist
    override val title = "Artists"

    override fun onCreateViewHolder(view: View) = MainViewHolder(view, presenter)

    override fun onBindNormalViewHolder(holder: MainViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}