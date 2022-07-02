package com.example.musicplayer.ui.track

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.db.Track

class TrackAdapter(
    private val context: Context,
    private val presenter: TrackPresenter)
    : RecyclerView.Adapter<TrackViewHolder>() {

    private var tracks: List<Track> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false)
        return TrackViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setTitle(tracks[position].title)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    fun update(newTracks: List<Track>) {
        tracks = newTracks
    }
}