package com.example.todorazorpay.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class ToDoModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("description")
    val description: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("date")
    val date: String
)


data class TodoResponse(
    @SerializedName("todos")
    val todos: List<ToDoModel>
)