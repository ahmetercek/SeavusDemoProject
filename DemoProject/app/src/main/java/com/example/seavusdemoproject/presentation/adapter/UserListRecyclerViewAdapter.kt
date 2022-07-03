package com.example.seavusdemoproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seavusdemoproject.databinding.LayoutUserListItemCellBinding
import com.example.seavusdemoproject.domain.model.User
import com.example.seavusdemoproject.presentation.listener.ItemClickListener

class UserListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<User> = ArrayList()
    private lateinit var mItemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutUserListItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(
            binding,
            mItemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is UserItemViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(userList: List<User>){
        items = userList
        notifyDataSetChanged()
    }

    class UserItemViewHolder
    constructor(
        val binding: LayoutUserListItemCellBinding,
        clickListener: ItemClickListener
    ): RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener{
                clickListener.clickPosition(adapterPosition)
            }
        }

        fun bind(user: User){
            binding.userName.text = user.name
            binding.userEmail.text = user.email

            Glide.with(itemView).load(user.photoUrl + ".png").into(binding.userPhoto)
        }

    }

}