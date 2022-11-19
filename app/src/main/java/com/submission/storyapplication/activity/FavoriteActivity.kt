package com.submission.storyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.R
import com.submission.storyapplication.adapter.FavoriteAdapter
import com.submission.storyapplication.adapter.StoriesAdapter
import com.submission.storyapplication.databinding.ActivityMainBinding
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.viewModel.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var viewModelScope = favoriteViewModel.viewModelScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()!!.setTitle("List Favorite Story");

        binding.listStory.layoutManager = LinearLayoutManager(this)

        getAllStoriesFavorite()
    }

    private fun getAllStoriesFavorite() {
        val adapter = FavoriteAdapter()
        binding.listStory.adapter = adapter
        viewModelScope.launch {
            favoriteViewModel.getAllFavorite().flowOn(Dispatchers.IO).collect { resource ->
                when (resource) {
                    is Resources.Success -> {
                        adapter.setListStories(resource.data as List<AllStoriesModel.stories>)
                    }
                    is Resources.Error -> {

                    }
                    is Resources.Loading -> {

                    }
                }
            }
        }
    }
}