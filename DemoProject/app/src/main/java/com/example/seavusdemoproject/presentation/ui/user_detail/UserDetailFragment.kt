package com.example.seavusdemoproject.presentation.ui.user_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.domain.model.Post
import com.example.seavusdemoproject.domain.model.Todo
import com.example.seavusdemoproject.presentation.adapter.PostListRecyclerViewAdapter
import com.example.seavusdemoproject.presentation.adapter.TodoListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    companion object {
        fun newInstance() = UserDetailFragment()
    }

    private val args: UserDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var userNameTextView: TextView
    private lateinit var listHeaderTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var showTodosButton: Button
    private lateinit var showPostsButton: Button
    private lateinit var todoRecyclerViewAdapter: TodoListRecyclerViewAdapter
    private lateinit var postRecyclerViewAdapter: PostListRecyclerViewAdapter
    private lateinit var todoList: List<Todo>
    private lateinit var postList: List<Post>
    private lateinit var loadingBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
        loadData()
    }

    private fun initViews(){
        userNameTextView = requireView().findViewById(R.id.user_name)
        userNameTextView.text = args.userName

        listHeaderTextView = requireView().findViewById(R.id.list_header)
        listHeaderTextView.text = "Todos"

        showTodosButton = requireView().findViewById(R.id.showTodos)
        showTodosButton.setOnClickListener {
            showTodoList()
        }
        showPostsButton = requireView().findViewById(R.id.showPost)
        showPostsButton.setOnClickListener {
            showPostList()
        }

        todoRecyclerViewAdapter = TodoListRecyclerViewAdapter()
        postRecyclerViewAdapter = PostListRecyclerViewAdapter()

        // Set recycleView.
        recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            //adapter = todoRecyclerViewAdapter
        }

        loadingBar = requireView().findViewById<ProgressBar>(R.id.loadingBar)

    }

    private fun initObservers(){

        viewModel.todos.observe(viewLifecycleOwner) { response ->
            todoList = response
            // sort todos item as first completed then not completed.
            todoRecyclerViewAdapter.setData(todoList.sortedByDescending { it.completed })
            loadingBar.visibility = View.INVISIBLE
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            postList = it
            postRecyclerViewAdapter.setData(postList)
        }
    }

    private fun loadData(){
        viewModel.fetchTodosByUserId(args.userId)
        viewModel.fetchPostsByUserId(args.userId)

        showTodoList()
    }

    private fun showTodoList(){
        listHeaderTextView.text = "Todos"
        recyclerView.adapter = todoRecyclerViewAdapter
    }

    private fun showPostList(){
        listHeaderTextView.text = "Posts"
        recyclerView.adapter = postRecyclerViewAdapter
    }
}