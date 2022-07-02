package com.example.seavusdemoproject.presentation.ui.user_list

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seavusdemoproject.R
import com.example.seavusdemoproject.presentation.adapter.UserListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.text.Editable
import com.example.seavusdemoproject.domain.model.User


@AndroidEntryPoint
class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: UserListRecyclerViewAdapter
    private lateinit var userList: List<User>
    private lateinit var filteredList: List<User>

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
            userList = it
            recyclerViewAdapter.setData(userList)
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

        editText = requireView().findViewById<EditText>(R.id.edit_text)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                searchUser(s.toString())
            }
        })
    }

    private fun searchUser(query: String) {
        if(query.isNullOrEmpty()){
            recyclerViewAdapter.setData(userList)
            return
        }
        val filteredList: List<User> = userList.filter{ user -> user.name.lowercase().contains(query.lowercase()) }.toList()
        recyclerViewAdapter.setData(filteredList)
    }
}

