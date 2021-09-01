package com.friendsflix.presentation.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.friendsflix.R
import com.friendsflix.databinding.ActivityMovieDetailBinding
import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.utils.extentions.setupRecyclerView
import com.google.android.material.textfield.TextInputLayout
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

        binding.commentbutton.setOnClickListener {
            (intent.extras?.getSerializable(MOVIE_ID) as? Args)?.let {
                val text = binding.commentEt.text.toString()
                binding.commentEt.setText("")
                viewModel.makeComment(text, it.movieId)
            }
        }
        setupViewModel()
        fetchMovieDetail()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is MovieDetailState.ShowMovieDetail -> showMovieDetail(state.movieDetail)
                is MovieDetailState.ShowError -> showError(state.message)
                is MovieDetailState.Loading -> showLoading(state.loading)
                MovieDetailState.Reload -> recreate()
            }
        }
    }

    private fun showMovieDetail(movieDetail: MovieDetail) {
        adapter.commentItems = movieDetail.comments
        binding.commentsRv.setupRecyclerView(adapter)

        binding.movieFavorite.setOnClickListener {
            viewModel.favoriteMovie(movieDetail.id, !movieDetail.favorite)
        }

        binding.rating.text = "Rating: " + movieDetail.rating

        binding.rating.setOnClickListener {
            showAlertWithTextInputLayout(movieDetail.id)
        }

        if (!movieDetail.favorite) {
            AppCompatResources.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)?.let {
                binding.movieFavorite.setImageDrawable(it)
            }
        }

        Glide.with(this)
            .load(movieDetail.imageUrl)
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

    private fun showAlertWithTextInputLayout(movieId: Int) {
        val textInputLayout = TextInputLayout(this)
        textInputLayout.setPadding(
            resources.getDimensionPixelOffset(R.dimen.dp_16), // if you look at android alert_dialog.xml, you will see the message textview have margin 14dp and padding 5dp. This is the reason why I use 19 here
            0,
            resources.getDimensionPixelOffset(R.dimen.dp_16),
            0
        )
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER
        textInputLayout.hint = "Rating"
        textInputLayout.addView(input)

        val alert = AlertDialog.Builder(this)
            .setTitle("Rating")
            .setView(textInputLayout)
            .setMessage("Avalie o filme")
            .setPositiveButton("Submit") { dialog, _ ->
                var rating = input.text.toString()
                if (rating.isEmpty()) {
                    rating = "0"
                }
                viewModel.rate(movieId, rating.toFloat())
                dialog.cancel()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }.create()

        alert.show()
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