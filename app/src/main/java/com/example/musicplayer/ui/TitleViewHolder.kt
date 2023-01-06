package com.example.musicplayer.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TitleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setTitle(title: String?) {
        (itemView as TextView).text = title
    }
}