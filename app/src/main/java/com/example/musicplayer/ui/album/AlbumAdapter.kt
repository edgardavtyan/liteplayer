package com.example.musicplayer.ui.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class AlbumAdapter(
    private val context: Context,
    private val model: AlbumModel,
    private val presenter: AlbumPresenter
) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false)
        return AlbumViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setTitle(model.getAlbumAt(position).title)
    }

    override fun getItemCount(): Int {
        return model.size
    }
}