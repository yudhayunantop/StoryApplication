package com.submission.storyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.R
import com.submission.storyapplication.adapter.StoriesAdapter
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.preferences.Preferences.clearData
import com.submission.storyapplication.preferences.Preferences.getToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endpoint}
    private lateinit var adapter: StoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Preferences.init(this)

        getSupportActionBar()!!.setTitle("List Story");

        adapter = StoriesAdapter()
        adapter.notifyDataSetChanged()

        list_story.layoutManager = LinearLayoutManager(this)
        list_story.adapter = adapter

        getAllStories()
        
        adapter.setOnItemClickCallback(object : StoriesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: AllStoriesModel.stories) {
//                Toast.makeText(applicationContext, "dor", Toast.LENGTH_SHORT).show()
                intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        })

        fab_create.setOnClickListener {
            intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllStories(){
            api.get_all_stories(token="Bearer ${getToken()}")
                .enqueue(object : Callback<AllStoriesModel> {
                    override fun onResponse(
                        call: Call<AllStoriesModel>,
                        response: Response<AllStoriesModel>
                    ) {
                        if (response.isSuccessful){
                            val submit = response.body()
                            adapter.setData(submit!!.listStory!!)
                        }else{
                            Toast.makeText(applicationContext, "Failed fetch data!!!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<AllStoriesModel>, t: Throwable) {
                        t.message
                        Toast.makeText(applicationContext, "No Connection!!!", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                val i = Intent(this, LoginActivity::class.java)
                clearData()
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}