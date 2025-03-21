package com.example.todorazorpay.viewModels

import android.os.Trace
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.repo.TodoRepository
import com.google.firebase.perf.FirebasePerformance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _totalTasks = MutableLiveData<Int>()
    val totalTasks: LiveData<Int> get() = _totalTasks

    private val _totalPendingTasks = MutableLiveData<Int>()
    val totalPendingTasks: LiveData<Int> get() = _totalPendingTasks

    private val _totalOngoingTasks = MutableLiveData<Int>()
    val totalOngoingTasks: LiveData<Int> get() = _totalOngoingTasks

    private val _totalCompletedTasks = MutableLiveData<Int>()
    val totalCompletedTasks: LiveData<Int> get() = _totalCompletedTasks

    val allTodos: LiveData<List<ToDoModel>> = repository.allTodos

    fun fetchTodos() {
        viewModelScope.launch {
            repository.fetchTodosFromApiAndStore()
        }
    }

    fun addTodo(todo: ToDoModel) {
        val trace: com.google.firebase.perf.metrics.Trace = FirebasePerformance.getInstance().newTrace("task_save_trace")
        trace.start()

        viewModelScope.launch {
            repository.addTodo(todo)
        }

        trace.stop()
    }

    fun updateTodo(todo: ToDoModel) {
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: ToDoModel) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun searchTodos(query: String): LiveData<List<ToDoModel>> {
        return repository.searchTodos(query)
    }

    fun fetchTaskCounts() {
        viewModelScope.launch {
            _totalTasks.value = repository.getTotalTasks()
            _totalPendingTasks.value = repository.getTotalPendingTasks()
            _totalOngoingTasks.value = repository.getTotalOngoingTasks()
            _totalCompletedTasks.value = repository.getTotalCompletedTasks()
        }
    }
}