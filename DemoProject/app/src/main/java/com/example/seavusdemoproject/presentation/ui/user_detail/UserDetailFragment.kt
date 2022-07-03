package com.example.seavusdemoproject.presentation.ui.user_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seavusdemoproject.databinding.UserDetailFragmentBinding
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

    private var _binding: UserDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: UserDetailFragmentArgs by navArgs()
    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var todoRecyclerViewAdapter: TodoListRecyclerViewAdapter
    private lateinit var postRecyclerViewAdapter: PostListRecyclerViewAdapter
    private lateinit var todoList: List<Todo>
    private lateinit var postList: List<Post>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        initObservers()
        loadData()
    }

    private fun initViews(){
        binding.userName.text = args.userName
        binding.listHeader.text = "Todos"

        binding.showTodos.setOnClickListener {
            showTodoList()
        }
        binding.showPost.setOnClickListener {
            showPostList()
        }

        todoRecyclerViewAdapter = TodoListRecyclerViewAdapter()
        postRecyclerViewAdapter = PostListRecyclerViewAdapter()

        // Set recycleView.
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers(){

        viewModel.todos.observe(viewLifecycleOwner) { response ->
            todoList = response
            // sort todos item as first completed then not completed.
            todoRecyclerViewAdapter.setData(todoList.sortedByDescending { it.completed })
            binding.loadingBar.visibility = View.INVISIBLE
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
        binding.listHeader.text = "Todos"
        binding.recyclerView.adapter = todoRecyclerViewAdapter
    }

    private fun showPostList(){
        binding.listHeader.text = "Posts"
        binding.recyclerView.adapter = postRecyclerViewAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}