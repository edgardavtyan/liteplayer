package com.example.musicplayer.ui.album

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class AlbumViewHolder(itemView: View, private val presenter: AlbumPresenter)
    : RecyclerView.ViewHolder(itemView) {

    private val titleView: TextView

    init {
        titleView = itemView.findViewById(R.id.titleView)
        itemView.setOnClickListener { presenter.onItemClick(bindingAdapterPosition) }
    }

    fun setTitle(title: String) {
        titleView.text = title
    }
}