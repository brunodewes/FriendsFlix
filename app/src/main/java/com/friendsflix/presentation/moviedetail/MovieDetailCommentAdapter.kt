package com.friendsflix.presentation.moviedetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieDetailCommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var commentItems: List<MovieDetailCommentItem> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = commentItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }
}