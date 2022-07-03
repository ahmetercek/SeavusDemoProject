package com.example.seavusdemoproject.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.databinding.LayoutTodoListItemCellBinding
import com.example.seavusdemoproject.domain.model.Todo

class TodoListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Todo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutTodoListItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(
            binding
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
        val binding: LayoutTodoListItemCellBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(todo: Todo){
            binding.todoTitle.text = todo.title
            if (todo.completed){
                binding.todoStatus.text = itemView.context.getString(R.string.completed_text)
                binding.todoStatus.setTextColor(Color.GREEN)
            }
            else {
                binding.todoStatus.text = itemView.context.getString(R.string.not_completed_text)
                binding.todoStatus.setTextColor(Color.RED)
            }
        }

    }

}