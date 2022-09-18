package com.submission.storyapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.models.AllStoriesModel
import kotlinx.android.synthetic.main.item_story.view.*

class StoriesAdapter(): RecyclerView.Adapter<StoriesAdapter.ListViewHolder>() {
    private val stories = ArrayList<AllStoriesModel.stories>()
    private var onItemClickCallback: OnItemClickCallback? = null

    public fun setData(data: List<AllStoriesModel.stories>) {
        stories.clear()
        stories.addAll(data)
        notifyDataSetChanged()
    }

//    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder {
//        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
//        return ListViewHolder(view)
//    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) : ListViewHolder{
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_story, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(stories[position])
    }
    override fun getItemCount() = stories.size


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stories: AllStoriesModel.stories) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(stories.photoUrl)
                    .apply(RequestOptions().override(55, 55))
                    .into(iv_item_photo)
                tv_item_name.text = stories.name
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(stories) }

            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: AllStoriesModel.stories)
    }
}