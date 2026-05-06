package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todolist.models.DatabaseInstance
import com.example.todolist.models.TodoDetails

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val db = DatabaseInstance.getDatabase(application)
    private val dao = db.todoDao()

    // 🔥 THIS IS REQUIRED FOR LIVE UI
    val allTodos: LiveData<List<TodoDetails>> = dao.getAll()

    fun insert(todo: TodoDetails) {
        Thread { dao.insert(todo) }.start()
    }

    fun update(todo: TodoDetails) {
        Thread { dao.update(todo) }.start()
    }

    fun delete(todo: TodoDetails) {
        Thread { dao.delete(todo) }.start()
    }

    fun getById(id: Int): TodoDetails {
        return dao.getById(id)
    }
}