package com.example.musicplayer.ui.album

import android.content.Context
import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Album
import com.example.musicplayer.ui.CustomAdapter

class AlbumAdapter(context: Context, private val presenter: AlbumPresenter)
    : CustomAdapter<AlbumViewHolder, Album>(context) {

    override val layout = R.layout.listitem_album

    override fun onCreateViewHolder(view: View) = AlbumViewHolder(view, presenter)

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}