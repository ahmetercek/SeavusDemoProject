package com.example.seavusdemoproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.domain.model.User

class UserListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list_item_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is UserItemViewHolder -> {
                holder.bind(items[position])
            }

        }
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
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        private val userName = itemView.findViewById<TextView>(R.id.user_name)
        private val userEmail = itemView.findViewById<TextView>(R.id.user_email)

        fun bind(user: User){
            userName.text = user.name
            userEmail.text = user.email
        }

    }

}