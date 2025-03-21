package com.example.todorazorpay.ui.addTask

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todorazorpay.MainApplication
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.R
import com.example.todorazorpay.databinding.FragmentAddTaskBinding
import com.example.todorazorpay.adapters.TodoAdapter
import com.example.todorazorpay.viewModels.TodoViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddTask : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private val todoViewModel: TodoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onclickd()
        setupSaveButton()
    }

    fun onclickd() {

        binding.toolbar.title = "Add Task"
        binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white, null))
        binding.etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth-${month + 1}-$year"
                    binding.etDate.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val date = binding.etDate.text.toString()

            val selectedStatus = when (binding.radioGroupStatus.checkedRadioButtonId) {
                R.id.rbPending -> 0
                R.id.rbOngoing -> 1
                R.id.rbCompleted -> 2
                else -> -1
            }

            if (title.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()) {
                val todo = ToDoModel(title = title, description = description, status = selectedStatus,date = date)
                todoViewModel.addTodo(todo)
                logTaskClickedEvent(todo)
                val bundle = Bundle().apply {
                    putString("test_param", "value_test")
                }
                FirebaseAnalytics.getInstance(requireContext()).logEvent("test_event", bundle)
                clearFields()
            }
        }
    }

    private fun clearFields() {
        binding.etTitle.text?.clear()
        binding.etDescription.text?.clear()
        binding.etDate.text?.clear()
    }
    private fun logTaskClickedEvent(todo: ToDoModel) {
        val bundle = Bundle().apply {
            putString("task_title", todo.title)
            putString("task_status", todo.status.toString())
            putString("task_date", todo.date)
        }
        MainApplication.logFirebaseEvent("task_added", bundle)
    }

}