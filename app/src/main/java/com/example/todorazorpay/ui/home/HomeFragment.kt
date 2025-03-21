package com.example.todorazorpay.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todorazorpay.MainApplication
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.R
import com.example.todorazorpay.adapters.TodoAdapter
import com.example.todorazorpay.databinding.FragmentHomeBinding

import com.example.todorazorpay.viewModels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeTodos()

        setupSwipeToDelete()

        binding.fabAddTask.setOnClickListener {
            todoViewModel.fetchTodos()
        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(mutableListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }
    }

    private fun observeTodos() {

        todoViewModel.fetchTaskCounts()

        todoViewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            if (todos.isNotEmpty()) {
                todoAdapter.updateList(todos)
                binding.fabAddTask.visibility= View.GONE
                binding.lottieAnimationView.visibility = View.GONE
            } else {
                binding.fabAddTask.visibility= View.VISIBLE
                binding.lottieAnimationView.visibility = View.VISIBLE
            }
        }

        todoViewModel.totalTasks.observe(viewLifecycleOwner) {

           binding.card1Text.text = "Total tasks: $it"
        }

        todoViewModel.totalPendingTasks.observe(viewLifecycleOwner) {
            binding.card3Text.text = "Pending: $it"
        }

        todoViewModel.totalOngoingTasks.observe(viewLifecycleOwner) {
            binding.card4Text.text = "Ongoing: $it"
        }

        todoViewModel.totalCompletedTasks.observe(viewLifecycleOwner) {
            binding.card2Text.text  = "Completed: $it"
        }


    }

    private fun setupSwipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val todo = todoAdapter.getTodoAt(position)
                logTaskClickedEvent(todo)
                todoViewModel.deleteTodo(todo)

                todoAdapter.removeItem(position)
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun logTaskClickedEvent(todo: ToDoModel) {
        val bundle = Bundle().apply {
            putString("task_title", todo.title)
            putString("task_status", todo.status.toString())
            putString("task_date", todo.date)
        }
        MainApplication.logFirebaseEvent("task_deleted", bundle)
    }
}