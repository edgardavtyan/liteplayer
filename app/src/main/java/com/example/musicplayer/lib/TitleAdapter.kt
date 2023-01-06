package com.example.musicplayer.lib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.ui.TitleViewHolder

abstract class TitleAdapter<T : RecyclerView.ViewHolder, TItem>()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_TITLE = 0
    private val TYPE_NORMAL = 1

    abstract val layout: Int

    abstract val title: String?

    abstract fun onCreateViewHolder(view: View): T
    abstract fun onBindNormalViewHolder(holder: T, position: Int)

    protected var list: List<TItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = if (viewType == TYPE_TITLE) R.layout.layout_title else layout
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        if (viewType == TYPE_TITLE) {
            return TitleViewHolder(view)
        } else {
            return onCreateViewHolder(view)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == TYPE_TITLE) {
            (holder as TitleViewHolder).setTitle(title)
        } else {
            onBindNormalViewHolder(holder as T, position - 1)
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_TITLE
        } else {
            return TYPE_NORMAL
        }
    }

    fun update(newList: List<TItem>) {
        list = newList
        notifyDataSetChanged()
    }
}