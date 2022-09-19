package com.submission.storyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.models.AllStoriesModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_story.view.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        tinggal masukkan data ke activity
        val data: AllStoriesModel.stories = intent.getSerializableExtra("data") as AllStoriesModel.stories
        Glide.with(this)
            .load(data.photoUrl)
            .apply(RequestOptions().override(55, 55))
            .into(iv_detail_photo)
        tv_detail_name.text = data.name
        tv_detail_description.text = data.description
        Toast.makeText(applicationContext, "Detail data!!!", Toast.LENGTH_SHORT).show()
    }
}