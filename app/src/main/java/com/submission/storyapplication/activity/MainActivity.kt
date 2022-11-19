package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.MainViewModel
import com.submission.storyapplication.R
import com.submission.storyapplication.adapter.StoriesAdapter
import com.submission.storyapplication.databinding.ActivityMainBinding
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.preferences.Preferences.clearData
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()!!.setTitle("List Story");

        Preferences.init(this)

        binding.listStory.layoutManager = LinearLayoutManager(this)

        getAllStories()

        fab_create.setOnClickListener {
            intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllStories(){
        val adapter= StoriesAdapter()
        binding.listStory.adapter=adapter
        mainViewModel.stories.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.maps ->{
                val i = Intent(this, MapsActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.logout -> {
                val i = Intent(this, LoginActivity::class.java)
                clearData()
                startActivity(i)
                finish()
                return true
            }
            R.id.favorite ->{
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}