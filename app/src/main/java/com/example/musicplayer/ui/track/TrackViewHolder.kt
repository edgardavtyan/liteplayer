package com.example.musicplayer.ui.track

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class TrackViewHolder(itemView: View, private val presenter: TrackPresenter)
    : RecyclerView.ViewHolder(itemView) {

    private val titleView: TextView

    init {
        titleView = itemView.findViewById(R.id.titleView)
        itemView.setOnClickListener { presenter.onItemClick(bindingAdapterPosition - 1) }
    }

    fun setTitle(title: String) {
        titleView.text = title
    }
}