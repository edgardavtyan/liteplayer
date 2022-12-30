package com.example.musicplayer.lib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CustomAdapter<T : RecyclerView.ViewHolder, TItem>(private val context: Context)
    : RecyclerView.Adapter<T>() {

    abstract val layout: Int

    abstract fun onCreateViewHolder(view: View): T

    protected var list: List<TItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val view = LayoutInflater.from(context).inflate(layout, parent, false)
        return onCreateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: List<TItem>) {
        list = newList
        notifyDataSetChanged()
    }
}