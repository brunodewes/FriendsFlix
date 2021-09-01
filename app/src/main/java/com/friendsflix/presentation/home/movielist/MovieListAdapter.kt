package com.friendsflix.presentation.home.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.friendsflix.databinding.ItemMovieListBinding
import com.friendsflix.domain.model.Movie

class MovieListAdapter(
    private val onMovieClickedListener: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            holder.bind(movies[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    internal inner class MovieViewHolder(
        private val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = binding.run {
            movieTitle.text = movie.title
            root.setOnClickListener { onMovieClickedListener(movie) }

            Glide
                .with(movieImage)
                .load(movie.imageUrl)
                .centerCrop()
                .into(movieImage)
        }
    }
}