package com.submission.storyapplication.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.activity.DetailActivity
import com.submission.storyapplication.core.databinding.ItemStoryBinding

class StoriesAdapter
    : PagingDataAdapter<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories, StoriesAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ListViewHolder(itemView: ItemStoryBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemStoryBinding = itemView

        fun bind(stories: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories) {
            Glide.with(itemView.context)
                .load(stories.photoUrl)
                .apply(RequestOptions().override(55, 55))
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = stories.name
            binding.tvItemDesc.text = stories.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("data", stories)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(binding.ivItemPhoto, "profile"),
                        Pair(binding.tvItemName, "name"),
                        Pair(binding.tvItemDesc, "description")
                    )

                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.submission.storyapplication.core.domain.models.AllStoriesModel.stories>() {
            override fun areItemsTheSame(
                oldItem: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories,
                newItem: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories,
                newItem: com.submission.storyapplication.core.domain.models.AllStoriesModel.stories
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}