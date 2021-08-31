package com.friendsflix.presentation.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.friendsflix.databinding.ActivityMovieDetailBinding
import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.model.MovieDetailComment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val adapter by lazy { MovieDetailCommentAdapter() }
    private val viewModel by viewModel<MovieDetailViewModel>()

    data class Args(val movieId: Int) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        fetchMovieDetail()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is MovieDetailState.ShowMovieDetail -> showMovieDetail(state.movieDetail)
                is MovieDetailState.ShowError -> showError(state.message)
                is MovieDetailState.Loading -> showLoading(state.loading)
                is MovieDetailState.UpdateComments -> updateComments(state.comments)
                is MovieDetailState.UpdateRating -> updateRating(state.rating)
                is MovieDetailState.UpdateFavorite -> updateFavorite(state.favorite)
            }
        }
    }

    private fun updateFavorite(favorite: Boolean) {
        TODO("Not yet implemented")
    }

    private fun updateRating(rating: Float) {
        TODO("Not yet implemented")
    }

    private fun updateComments(comments: List<MovieDetailComment>) {
        TODO("Not yet implemented")
    }

    private fun showMovieDetail(movieDetail: MovieDetail) {
        // binding.commentsRv.setupRecyclerView(adapter)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + movieDetail.imageUrl)
            .centerCrop()
            .into(binding.movieImage)
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(loading: Boolean) {
    }

    private fun fetchMovieDetail() {
        (intent.extras?.getSerializable(MOVIE_ID) as? Args)?.let {
            viewModel.fetchMovieDetails(it.movieId)
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun newInstance(movieId: Int, context: Context) {
            context.startActivity(
                Intent(context, MovieDetailActivity::class.java).apply {
                    putExtra(MOVIE_ID, Args(movieId))
                }
            )
        }
    }
}