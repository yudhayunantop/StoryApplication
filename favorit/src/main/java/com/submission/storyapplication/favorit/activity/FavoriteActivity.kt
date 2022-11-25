package com.submission.storyapplication.favorit.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.favorit.adapter.FavoriteAdapter
import com.submission.storyapplication.favorit.databinding.ActivityFavoriteBinding
import com.submission.storyapplication.favorit.di.favoriteModule
import com.submission.storyapplication.favorit.viewModel.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)

        supportActionBar?.title = "List Favorite Story"

        binding.listFavoriteStory.layoutManager = LinearLayoutManager(this)
        initializeAdapter()
        getAllStoriesFavorite()
    }

    private fun onClickList(adapter: FavoriteAdapter) {
        adapter.setOnClickListener(object : FavoriteAdapter.OnClickListener {
            override fun onButtonDeleteSelected(selectedStories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
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
        binding.listFavoriteStory.adapter = adapter
    }

    private fun refreshDataAdapter(listStories: List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>) {
        adapter.setListStories(listStories)
        adapter.notifyDataSetChanged()
    }

    private fun getAllStoriesFavorite() {
        favoriteViewModel.viewModelScope.launch {
            favoriteViewModel.getAllFavorite().flowOn(Dispatchers.IO).collect { resource ->
                when (resource) {
                    is Resources.Success -> {
                        refreshDataAdapter(resource.data as List<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>)
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