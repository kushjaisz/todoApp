package com.example.todorazorpay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todorazorpay.Models.ToDoModel
import com.example.todorazorpay.databinding.ItemTodoBinding

class TodoAdapter(private val todoList: MutableList<ToDoModel>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: ToDoModel) {
            binding.tvTitle.text = todo.title
            binding.tvDescription.text = todo.description
            binding.tvDate.text = todo.date
            binding.tvStatus.text = when (todo.status) {
                0 -> "Pending"
                1 -> "Ongoing"
                2 -> "Completed"
                else -> "Deleted"
            }        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size

    fun updateList(newList: List<ToDoModel>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    fun getTodoAt(position: Int): ToDoModel = todoList[position]


    fun removeItem(position: Int) {
        todoList.removeAt(position)
        notifyItemRemoved(position)
    }
}
