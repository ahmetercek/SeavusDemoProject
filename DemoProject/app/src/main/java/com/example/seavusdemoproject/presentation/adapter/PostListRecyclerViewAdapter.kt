package com.example.seavusdemoproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.domain.model.Post

class PostListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Post> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is PostItemViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(postList: List<Post>){
        items = postList
        notifyDataSetChanged()
    }

    class PostItemViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        private val postTitle = itemView.findViewById<TextView>(R.id.post_title)
        private val postBody = itemView.findViewById<TextView>(R.id.post_body)

        fun bind(post: Post){
            postTitle.text = post.title
            postBody.text = post.body
        }

    }

}