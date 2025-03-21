package com.example.todorazorpay.Network

import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.Models.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("e29b-9638-42a2-b871")
    suspend fun fetchData(): Response<TodoResponse>
}