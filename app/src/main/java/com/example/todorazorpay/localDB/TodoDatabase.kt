package com.example.todorazorpay.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todorazorpay.Models.ToDoModel

@Database(entities = [ToDoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}