package com.submission.storyapplication.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.R
import com.submission.storyapplication.adapter.FavoriteAdapter
import com.submission.storyapplication.databinding.ActivityMainBinding
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.viewModel.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "List Favorite Story"

        binding.listStory.layoutManager = LinearLayoutManager(this)
        initializeAdapter()
        getAllStoriesFavorite()
    }

    private fun onClickList(adapter: FavoriteAdapter) {
        adapter.setOnClickListener(object : FavoriteAdapter.OnClickListener {
            override fun onButtonDeleteSelected(selectedStories: AllStoriesModel.stories) {
                Toast.makeText(this@FavoriteActivity, "Stories Item Deleted", Toast.LENGTH_SHORT).show()
                favoriteViewModel.viewModelScope.launch {
                    favoriteViewModel.delete(selectedStories)
                    getAllStoriesFavorite()
                }
            }
        })
    }

    private fun initializeAdapter() {
        adapter = FavoriteAdapter()
        onClickList(adapter)
        binding.listStory.adapter = adapter
    }

    private fun refreshDataAdapter(listStories: List<AllStoriesModel.stories>) {
        adapter.setListStories(listStories)
        adapter.notifyDataSetChanged()
    }

    private fun getAllStoriesFavorite() {
        favoriteViewModel.viewModelScope.launch {
            favoriteViewModel.getAllFavorite().flowOn(Dispatchers.IO).collect { resource ->
                when (resource) {
                    is Resources.Success -> {
                        refreshDataAdapter(resource.data as List<AllStoriesModel.stories>)
                    }
                    is Resources.Error -> {
                        refreshDataAdapter(listOf())
                    }
                    is Resources.Loading -> {

                    }
                }
            }
        }
    }
}