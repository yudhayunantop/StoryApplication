package com.submission.storyapplication.favorit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.submission.storyapplication.core.data.remote.response.AllStoriesModel
import com.submission.storyapplication.core.databinding.ItemStoryFavoriteBinding

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


    class ViewHolder(itemView: ItemStoryFavoriteBinding) : RecyclerView.ViewHolder(itemView.root) {
         var binding: ItemStoryFavoriteBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = ItemStoryFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listStories[position]
        with(holder){
            binding.tvItemName.text = current.name
            binding.tvItemDesc.text = current.description
            binding.btnDelete.setOnClickListener {
                onClickListener.onButtonDeleteSelected(current)
            }
        }
    }

    override fun getItemCount(): Int = listStories.size

    interface OnClickListener{
        fun onButtonDeleteSelected(selectedStories: AllStoriesModel.stories)
    }
}