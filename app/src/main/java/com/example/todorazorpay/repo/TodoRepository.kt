package com.example.todorazorpay.repo

import androidx.lifecycle.LiveData
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.Network.ApiService
import com.example.todorazorpay.localDB.TodoDao
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao,
    private val todoApiService: ApiService
) {

    val allTodos: LiveData<List<ToDoModel>> = todoDao.getAllTodos()

    suspend fun fetchTodosFromApiAndStore() {
        val response = todoApiService.fetchData()
        if (response.isSuccessful) {
            response.body()?.todos?.let { todos ->
                if (todos.isNotEmpty()) {
                    todoDao.insertAll(todos)
                }
            }
        }
    }

    suspend fun addTodo(todo: ToDoModel) {
        todoDao.insert(todo)
    }

    suspend fun updateTodo(todo: ToDoModel) {
        todoDao.update(todo)
    }

    suspend fun deleteTodo(todo: ToDoModel) {
        todoDao.delete(todo)
    }

    fun searchTodos(query: String): LiveData<List<ToDoModel>> {
        return todoDao.searchTodos("%$query%")
    }


    suspend fun getTotalTasks(): Int = todoDao.getTotalTasks()
    suspend fun getTotalPendingTasks(): Int = todoDao.getTotalPendingTasks()
    suspend fun getTotalOngoingTasks(): Int = todoDao.getTotalOngoingTasks()
    suspend fun getTotalCompletedTasks(): Int = todoDao.getTotalCompletedTasks()
}
