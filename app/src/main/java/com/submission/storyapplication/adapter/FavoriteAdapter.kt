package com.submission.storyapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.submission.storyapplication.R
import com.submission.storyapplication.domain.models.AllStoriesModel

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


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var image = itemView.findViewById<ImageView>(R.id.iv_item_photo)
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_item_name)
        var tvDesc = itemView.findViewById<TextView>(R.id.tv_item_desc)
        var buttonDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_story_favorite, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listStories[position]
        with(holder){
            this.tvTitle.text = current.name
            this.tvDesc.text = current.description
//            this.image.setBackgroundResource(R.drawable.ic_baseline_image_24)
            this.buttonDelete.setOnClickListener {
                onClickListener.onButtonDeleteSelected(current)
            }
        }
    }

    override fun getItemCount(): Int = listStories.size

    interface OnClickListener{
        fun onButtonDeleteSelected(selectedStories: AllStoriesModel.stories)
    }
}