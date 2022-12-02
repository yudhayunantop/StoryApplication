package com.submission.storyapplication.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.databinding.ActivityDetailBinding
import com.submission.storyapplication.ui.viewModel.DetailViewModel
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
        binding.fabFavorite.visibility= View.GONE
        binding.fabDelete.visibility= View.GONE

//        tinggal masukkan data ke activity
        val data: AllStoriesModel.stories = intent.getSerializableExtra("data") as AllStoriesModel.stories
        Glide.with(this)
            .load(data.photoUrl)
            .apply(RequestOptions().override(55, 55))
            .into(binding.ivDetailPhoto)
        binding.tvDetailName.text = data.name
        binding.tvDetailDescription.text = data.description

        DetailViewModel.viewModelScope.launch(Dispatchers.IO) {
            DetailViewModel.isRowExist(data.id).collect{
                when(it){
                    is Resources.Success->{
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            if (it.data==true){
                                binding.fabDelete.visibility= View.VISIBLE
                                binding.fabDelete.setOnClickListener {
                                    DetailViewModel.viewModelScope.launch(Dispatchers.IO){
                                        DetailViewModel.delete(data).collect{
                                            when(it){
                                                is Resources.Success->{
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, "Favorite Deleted!!!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                                is Resources.Error -> {
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
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
                                    binding.fabDelete.visibility= View.GONE
                                    binding.fabFavorite.visibility= View.VISIBLE
                                }
                            }else{
                                binding.fabFavorite.visibility= View.VISIBLE
                                binding.fabFavorite.setOnClickListener {
                                    DetailViewModel.viewModelScope.launch(Dispatchers.IO){
                                        DetailViewModel.addFavorite(data).collect{
                                            when(it){
                                                is Resources.Success->{
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, "Favorite Added!!!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                                is Resources.Error -> {
                                                    DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                                                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
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
                                binding.fabFavorite.visibility= View.GONE
                                binding.fabDelete.visibility= View.VISIBLE
                            }
                        }
                    }
                    is Resources.Error -> {
                        DetailViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
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