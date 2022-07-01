package com.example.musicplayer.track

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView

    init {
        titleView = itemView.findViewById(R.id.titleView)
    }

    fun setTitle(title: String) {
        titleView.text = title
    }
}