package com.example.musicplayer.track

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class TrackAdapter(
    private val context: Context,
    private val model: TrackModel,
    private val presenter: TrackPresenter)
    : RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false)
        return TrackViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setTitle(model.getTrackAt(position).title)
    }

    override fun getItemCount(): Int {
        return model.itemCount
    }
}