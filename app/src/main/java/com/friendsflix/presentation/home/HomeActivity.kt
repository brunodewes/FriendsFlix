package com.friendsflix.presentation.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.databinding.ActivityHomeBinding
import com.friendsflix.domain.model.Movie
import com.friendsflix.presentation.home.movielist.MovieCategoryListAdapter
import com.friendsflix.utils.extentions.setupRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var adapter: MovieCategoryListAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupViewModel()
        viewModel.getPopularMovies()
    }

    private fun setupRecyclerView() {
        adapter = MovieCategoryListAdapter { Log.d("Movie clicked", "setupRecyclerView: ${it.id}") }
        binding.movieCategoriesRv.setupRecyclerView(adapter)
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is HomeState.Movies -> showMovies(state.movies)
                is HomeState.Loading -> showLoading(state.loading)
                is HomeState.Error -> showError(state.message)
            }
        }
    }

    private fun showMovies(movies: List<Movie>) {
        adapter.movies = movies
    }

    private fun showLoading(loading: Boolean) {

    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}