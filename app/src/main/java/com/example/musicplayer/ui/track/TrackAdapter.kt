package com.example.musicplayer.ui.track

import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Track
import com.example.musicplayer.lib.TitleAdapter

class TrackAdapter(private val presenter: TrackPresenter, albumTitle: String)
    : TitleAdapter<TrackViewHolder, Track>() {

    override val layout = R.layout.listitem_album

    override val title = albumTitle

    override fun onCreateViewHolder(view: View) = TrackViewHolder(view, presenter)

    override fun onBindNormalViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}