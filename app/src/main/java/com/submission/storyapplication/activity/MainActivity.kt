package com.submission.storyapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.storyapplication.R
import com.submission.storyapplication.adapter.StoriesAdapter
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.models.LoginModel
import com.submission.storyapplication.preferences.Preferences
import com.submission.storyapplication.preferences.Preferences.getToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.Request
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

        adapter = StoriesAdapter()
        adapter.notifyDataSetChanged()
        list_story.layoutManager = LinearLayoutManager(this)
        list_story.adapter = adapter

        getAllStories()
        
        adapter.setOnItemClickCallback(object : StoriesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: AllStoriesModel.stories) {
                Toast.makeText(applicationContext, "dor", Toast.LENGTH_SHORT).show()
//                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
//                moveIntent.putExtra(DetailActivity.EXTRA_USER, data)
//                moveIntent.putExtra(DetailActivity.EXTRA_AVATAR, data)
//                startActivity(moveIntent)
            }
        })

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
                        }
                    }

                    override fun onFailure(call: Call<AllStoriesModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "No Connection!!!", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }