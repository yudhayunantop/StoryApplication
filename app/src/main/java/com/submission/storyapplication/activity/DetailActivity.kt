package com.submission.storyapplication.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.databinding.ActivityDetailBinding
import com.submission.storyapplication.core.domain.models.AllStoriesModel
import com.submission.storyapplication.core.helper.Resources
import com.submission.storyapplication.viewModel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val DetailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()!!.setTitle("Detail Story");

//        tinggal masukkan data ke activity
        val data: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories = intent.getSerializableExtra("data") as com.submission.storyapplication.core.domain.models.AllStoriesModel.stories
        Glide.with(this)
            .load(data.photoUrl)
            .apply(RequestOptions().override(55, 55))
            .into(binding.ivDetailPhoto)
        binding.tvDetailName.text = data.name
        binding.tvDetailDescription.text = data.description

        binding.fabFavorite.setOnClickListener {
            DetailViewModel.viewModelScope.launch(Dispatchers.IO){
//                val check = DetailViewModel.checkFavorite()
                val response= DetailViewModel.addFavorite(data)
                when(response){
                    is Resources.Success->{
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Favorite Added!!!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is Resources.Error -> {
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    is Resources.Loading-> {
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}