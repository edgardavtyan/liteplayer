package com.example.musicplayer.ui.track

import android.content.Context
import android.view.View
import com.example.musicplayer.R
import com.example.musicplayer.db.Track
import com.example.musicplayer.lib.CustomAdapter

class TrackAdapter(context: Context, private val presenter: TrackPresenter)
    : CustomAdapter<TrackViewHolder, Track>(context) {

    override val layout = R.layout.listitem_album

    override fun onCreateViewHolder(view: View) = TrackViewHolder(view, presenter)

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setTitle(list[position].title)
    }
}