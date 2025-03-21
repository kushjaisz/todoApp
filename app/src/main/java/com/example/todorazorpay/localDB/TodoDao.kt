package com.example.todorazorpay.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todorazorpay.Models.ToDoModel
@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<ToDoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: ToDoModel)

    @Update
    suspend fun update(todo: ToDoModel)

    @Delete
    suspend fun delete(todo: ToDoModel)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllTodos(): LiveData<List<ToDoModel>>

    @Query("SELECT * FROM todos WHERE title LIKE :searchQuery OR description LIKE :searchQuery ORDER BY id DESC")
    fun searchTodos(searchQuery: String): LiveData<List<ToDoModel>>

    @Query("SELECT COUNT(*) FROM todos")
    suspend fun getTotalTasks(): Int

    @Query("SELECT COUNT(*) FROM todos WHERE status = 0")
    suspend fun getTotalPendingTasks(): Int

    @Query("SELECT COUNT(*) FROM todos WHERE status = 1")
    suspend fun getTotalOngoingTasks(): Int

    @Query("SELECT COUNT(*) FROM todos WHERE status = 2")
    suspend fun getTotalCompletedTasks(): Int
}
