package com.example.todolist.RecycleViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.models.TodoDetails

class TodoRecycleViewAdapter(
    private var todoDetails: List<TodoDetails>,
    private val onItemClick: (TodoDetails) -> Unit
) : RecyclerView.Adapter<TodoRecycleViewAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val detailTextView: TextView = itemView.findViewById(R.id.textViewDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoDetails[position]

        holder.titleTextView.text = todo.title
        holder.detailTextView.text = todo.details

        holder.itemView.setOnClickListener {
            onItemClick(todo)
        }
    }

    fun updateList(newList: List<TodoDetails>) {
        todoDetails = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = todoDetails.size
}