package com.example.musicplayer.ui.artist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R

class MainViewHolder(itemView: View, private val presenter: MainPresenter)
        : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView

    init {
        titleView = itemView.findViewById(R.id.titleView)
        titleView.setOnClickListener { presenter.onItemClick(bindingAdapterPosition) }
    }

    fun setTitle(title: String) {
        titleView.text = title
    }
}
