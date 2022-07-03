package com.example.seavusdemoproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.databinding.LayoutPostListItemCellBinding
import com.example.seavusdemoproject.domain.model.Post

class PostListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Post> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutPostListItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(
            binding
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
        val binding: LayoutPostListItemCellBinding,
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(post: Post){
            binding.postTitle.text = post.title
            binding.postBody.text = post.body
        }

    }

}