package com.example.seavusdemoproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.domain.model.Todo

class TodoListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Todo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_todo_list_item_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is TodoItemViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(todoList: List<Todo>){
        items = todoList
        notifyDataSetChanged()
    }

    class TodoItemViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        private val todoTitle = itemView.findViewById<TextView>(R.id.todo_title)
        private val todoStatus = itemView.findViewById<TextView>(R.id.todo_status)

        fun bind(todo: Todo){
            todoTitle.text = todo.title
            todoStatus.text = if (todo.completed)  "Completed" else "Not Completed"
        }

    }

}