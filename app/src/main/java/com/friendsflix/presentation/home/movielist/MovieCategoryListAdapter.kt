package com.friendsflix.presentation.home.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.friendsflix.databinding.ItemMovieCategoryListBinding
import com.friendsflix.domain.model.Movie
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.utils.extentions.setupRecyclerView

class MovieCategoryListAdapter(
    private val onMovieClickedListener: (Movie) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories: List<MovieCategory> = emptyList()
    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            categories = getCategories(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieCategoryViewHolder) {
            val category = categories[position]
            holder.bind(category, movies.filter { it.category == category })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCategoryViewHolder(binding)
    }

    private fun getCategories(movies: List<Movie>): List<MovieCategory> = movies.distinctBy { it.category }.map { it.category }

    internal inner class MovieCategoryViewHolder(
        private val binding: ItemMovieCategoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MovieCategory, movies: List<Movie>) = binding.run {
            val adapter = MovieListAdapter(onMovieClickedListener)
            adapter.movies = movies

            categoryTitleTv.text = category.title
            moviesRv.setupRecyclerView(adapter, RecyclerView.HORIZONTAL)
        }
    }
}