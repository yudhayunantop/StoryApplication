package com.submission.storyapplication.favorit.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.databinding.ItemStoryBinding
import com.submission.storyapplication.ui.activity.DetailActivity

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var listStories = listOf<AllStoriesModel.stories>()
    lateinit var onClickListener : OnClickListener

    @JvmName("setListStories1")
    fun setListStories(list: List<AllStoriesModel.stories>) {
        this.listStories = list
    }

    @JvmName("setOnClickListener1")
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }


    class ViewHolder(itemView: ItemStoryBinding) : RecyclerView.ViewHolder(itemView.root) {
         var binding: ItemStoryBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listStories[position]
        with(holder){
            Glide.with(itemView.context)
                .load(current.photoUrl)
                .apply(RequestOptions().override(55, 55))
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = current.name
            binding.tvItemDesc.text = current.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("data", current)

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

    override fun getItemCount(): Int = listStories.size

    interface OnClickListener{
        fun onButtonDeleteSelected(selectedStories: AllStoriesModel.stories)
    }
}