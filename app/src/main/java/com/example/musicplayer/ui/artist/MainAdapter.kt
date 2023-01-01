package com.example.musicplayer.ui.artist

import android.content.Context
import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Artist
import com.example.musicplayer.lib.CustomAdapter

class MainAdapter(context: Context, private val presenter: MainPresenter)
    : CustomAdapter<MainViewHolder, Artist>(context) {

    override val layout = R.layout.listitem_artist

    override fun onCreateViewHolder(view: View) = MainViewHolder(view, presenter)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}