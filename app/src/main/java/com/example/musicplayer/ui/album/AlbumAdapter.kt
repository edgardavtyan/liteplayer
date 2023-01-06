package com.example.musicplayer.ui.album

import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Album
import com.example.musicplayer.lib.TitleAdapter

class AlbumAdapter(private val presenter: AlbumPresenter)
    : TitleAdapter<AlbumViewHolder, Album>() {

    override val layout = R.layout.listitem_album
    override val title = presenter.title

    override fun onCreateViewHolder(view: View) = AlbumViewHolder(view, presenter)

    override fun onBindNormalViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}