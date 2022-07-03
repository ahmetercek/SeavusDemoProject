package com.example.seavusdemoproject.presentation.ui.user_list

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seavusdemoproject.presentation.adapter.UserListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.text.Editable
import androidx.navigation.Navigation
import com.example.seavusdemoproject.databinding.UserListFragmentBinding
import com.example.seavusdemoproject.domain.model.User
import com.example.seavusdemoproject.presentation.listener.ItemClickListener


@AndroidEntryPoint
class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var recyclerViewAdapter: UserListRecyclerViewAdapter
    private lateinit var userList: List<User>
    private lateinit var filteredList: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserListFragmentBinding.inflate(inflater, container, false)
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
        // Set searchBox.
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                searchUser(s.toString())
            }
        })

        // Set recycleView adapter
        recyclerViewAdapter = UserListRecyclerViewAdapter()
        recyclerViewAdapter.setItemClickListener(object : ItemClickListener {
            override fun clickPosition(position: Int) {
                // Navigate detail page with user info.
                val selectedUser = filteredList[position]
                navigateDetailPage(selectedUser.id, selectedUser.name)
            }
        })

        // Set recycleView.
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }

    }

    private fun initObservers(){
        viewModel.users.observe(viewLifecycleOwner) {
            userList = it
            recyclerViewAdapter.setData(userList)
            searchUser(String()) // Arrange filteredList state with emptyString.
            binding.loadingBar.visibility = View.INVISIBLE
        }
    }

    private fun loadData(){
        viewModel.fetchUsers()
    }

    private fun searchUser(query: String) {
        filteredList = userList.filter{ user -> user.name.lowercase().contains(query.lowercase()) }.toList()
        recyclerViewAdapter.setData(filteredList)
    }

    private fun navigateDetailPage(userId: Int, userName: String){
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(userId, userName)
        Navigation.findNavController(requireView()).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

