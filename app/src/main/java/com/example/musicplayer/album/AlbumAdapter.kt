package com.example.musicplayer.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.db.Album

class AlbumAdapter(private val context: Context, private val albums: List<Album>)
    : RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.setTitle(albums[position].title)
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}