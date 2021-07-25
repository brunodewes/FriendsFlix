package com.friendsflix.presentation.moviedetail

    import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.databinding.ActivityMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
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
                is MovieDetailState.ShowMovieDetail ->
                is MovieDetailState.ShowError ->
                is MovieDetailState.Loading ->
                is MovieDetailState.UpdateComments ->
                is MovieDetailState.UpdateRating ->
                is MovieDetailState.UpdateFavorite ->
            }
        }
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