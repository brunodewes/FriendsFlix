package com.friendsflix.presentation.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.databinding.ActivityProfileBinding
import com.friendsflix.presentation.moviedetail.MovieDetailCommentAdapter
import com.friendsflix.utils.extentions.setupRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val adapter by lazy { MovieDetailCommentAdapter() }
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.state.observe(this) {
            when (it) {
                is ProfileState.ShowProfile -> {
                    with(binding) {
                        name.text = it.profile.name
                        age.text = it.profile.age.toString()
                        commentsRv.setupRecyclerView(adapter.apply { it.profile.comments })
                    }
                }
            }
        }
        viewModel.getProfile()
    }
}