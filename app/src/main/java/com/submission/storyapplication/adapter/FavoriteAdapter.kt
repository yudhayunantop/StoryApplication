package com.submission.storyapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.submission.storyapplication.R
import com.submission.storyapplication.domain.models.AllStoriesModel

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var listStories = listOf<AllStoriesModel.stories>()

    @JvmName("setListStories1")
    fun setListStories(list: List<AllStoriesModel.stories>) {
        this.listStories = list
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_item_name)
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_item_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listStories[position]
        with(holder){
            this.tvTitle.text = current.name
            this.tvDesc.text = current.description
        }


    }

    override fun getItemCount(): Int = listStories.size
}