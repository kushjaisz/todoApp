package com.example.todorazorpay.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todorazorpay.MainApplication
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.R
import com.example.todorazorpay.adapters.TodoAdapter
import com.example.todorazorpay.databinding.FragmentSearchBinding
import com.example.todorazorpay.viewModels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Search"
        binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white, null))

        setupRecyclerView()
        setupSearchView()
        binding.searchView.setIconifiedByDefault(false)

        binding.searchView.clearFocus()
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(mutableListOf())
        binding.recyclerViewSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let { searchQuery ->
                    observeSearchResults(searchQuery)
                    binding.lottieAnimationView.visibility = View.GONE
                    logTaskClickedEvent(searchQuery)

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let { searchQuery ->
                    observeSearchResults(searchQuery)

                    binding.lottieAnimationView.visibility = View.GONE
                    logTaskClickedEvent(searchQuery)
                }
                return true
            }
        })
    }

    private fun observeSearchResults(query: String) {
        todoViewModel.searchTodos(query).observe(viewLifecycleOwner) { results ->
            if (results.isEmpty()){
                binding.lottieAnimationView.visibility = View.VISIBLE
            }
            todoAdapter.updateList(results)
        }

    }
    private fun logTaskClickedEvent(todo: String) {
        val bundle = Bundle().apply {
            putString("task_searched", todo)
            }
        MainApplication.logFirebaseEvent("todo_searched", bundle)
    }

}