package com.submission.storyapplication.favorit.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.favorit.databinding.ActivityFavoriteBinding
import com.submission.storyapplication.favorit.di.favoriteModule
import com.submission.storyapplication.core.ui.FavoriteAdapter
import com.submission.storyapplication.core.ui.StoriesAdapter
import com.submission.storyapplication.favorit.ui.viewModel.FavoriteViewModel
import com.submission.storyapplication.ui.activity.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    lateinit var adapter: FavoriteAdapter

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

    private fun initializeAdapter() {
        adapter = FavoriteAdapter()
        binding.listFavoriteStory.adapter = adapter
    }

    private fun refreshDataAdapter(listStories: List<AllStoriesModel.stories>) {
        adapter.setListStories(listStories)
        adapter.notifyDataSetChanged()

        adapter.setOnClickListener(object : FavoriteAdapter.OnClickListener{
            override fun onItemClick(stories: AllStoriesModel.stories) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra("data", stories)
                startActivity(intent)
            }
        })
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