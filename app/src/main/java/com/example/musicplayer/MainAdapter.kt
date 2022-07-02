package com.example.musicplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.db.Artist

class MainAdapter(
        private val context: Context,
        private val presenter: MainPresenter,
        private val artists: List<Artist>) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_artist, parent, false)
        return MainViewHolder(view, presenter)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setTitle(artists[position].title)
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}