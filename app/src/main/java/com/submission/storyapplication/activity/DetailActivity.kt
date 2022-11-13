package com.submission.storyapplication.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.domain.models.AllStoriesModel
import com.submission.storyapplication.helper.Resources
import com.submission.storyapplication.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val DetailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getSupportActionBar()!!.setTitle("Detail Story");

//        tinggal masukkan data ke activity
        val data: AllStoriesModel.stories = intent.getSerializableExtra("data") as AllStoriesModel.stories
        Glide.with(this)
            .load(data.photoUrl)
            .apply(RequestOptions().override(55, 55))
            .into(iv_detail_photo)
        tv_detail_name.text = data.name
        tv_detail_description.text = data.description

        fab_favorite.setOnClickListener {
            DetailViewModel.viewModelScope.launch(Dispatchers.IO){
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