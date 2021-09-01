package com.friendsflix.presentation.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.databinding.ActivityProfileBinding
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.domain.model.Movie
import com.friendsflix.presentation.home.movielist.MovieCategoryListAdapter
import com.friendsflix.presentation.moviedetail.MovieDetailActivity
import com.friendsflix.presentation.moviedetail.MovieDetailCommentAdapter
import com.friendsflix.utils.extentions.setupRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var adapter: MovieCategoryListAdapter
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MovieCategoryListAdapter { openMovieDetailActivity(it.id) }
        binding.movieCategoriesRv.setupRecyclerView(adapter)
        viewModel.state.observe(this) {
            when (it) {
                is ProfileState.ShowProfile -> {
                    with(binding) {
                        name.text = "Nome: " + it.profile.name
                        age.text = "Idade: " +  it.profile.age.toString()
                        adapter.movies = it.profile.movies.toMovie(MovieCategory.FAVORITE)
                    }
                }
            }
        }
        viewModel.getProfile()
    }

    private fun openMovieDetailActivity(movieId: Int) {
        MovieDetailActivity.newInstance(movieId, this)
    }

    private fun MovieCategoryResponse.toMovie(category: MovieCategory): List<Movie> {
        return this.movies.map {
            Movie(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
                category = category
            )
        }
    }
}