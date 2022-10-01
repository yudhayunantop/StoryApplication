package com.submission.storyapplication.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.activity.DetailActivity
import com.submission.storyapplication.models.AllStoriesModel
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class StoriesAdapter
    : PagingDataAdapter<AllStoriesModel.stories, StoriesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) : ListViewHolder{
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_story, viewGroup, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        private var tvName: TextView = itemView.findViewById(R.id.tv_item_name)

        fun bind(stories: AllStoriesModel.stories) {
                Glide.with(itemView.context)
                    .load(stories.photoUrl)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgPhoto)
                tvName.text = stories.name

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("data", stories)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imgPhoto, "profile"),
                            Pair(tvName, "name"),
                        )

                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllStoriesModel.stories>() {
            override fun areItemsTheSame(oldItem: AllStoriesModel.stories, newItem: AllStoriesModel.stories): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AllStoriesModel.stories, newItem: AllStoriesModel.stories): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}