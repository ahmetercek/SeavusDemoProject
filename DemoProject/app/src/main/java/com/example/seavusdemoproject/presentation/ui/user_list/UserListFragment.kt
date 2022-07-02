package com.example.seavusdemoproject.presentation.ui.user_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.presentation.adapter.UserListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: UserListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()

        viewModel.users.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setData(it)
        }
        viewModel.fetchUsers()
    }

    private fun initViews(){
        recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerViewAdapter = UserListRecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }

    }
}

