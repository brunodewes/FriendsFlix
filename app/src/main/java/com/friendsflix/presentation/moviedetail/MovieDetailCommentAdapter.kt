package com.friendsflix.presentation.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.friendsflix.databinding.ItemCommentBinding
import com.friendsflix.domain.model.MovieDetailComment

class MovieDetailCommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var commentItems: List<MovieDetailComment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = commentItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentViewHolder).bind(commentItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    inner class CommentViewHolder(
        private val binding: ItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(c: MovieDetailComment) {
            with(binding) {
                comment.text = c.comment
                date.text = c.date
                user.text = c.user.name
            }
        }
    }
}