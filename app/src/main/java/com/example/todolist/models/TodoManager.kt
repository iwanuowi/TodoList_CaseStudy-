package com.example.todolist.models

import com.example.todolist.mockDatas.MockTodoData

object TodoManager {

    private val todoList = mutableListOf<TodoDetails>()

    init {
        todoList.addAll(MockTodoData.populateData())
    }

    fun getAllTodos(): List<TodoDetails> {
        return todoList
    }

    fun getNewTodos(): List<TodoDetails> {
        return todoList.filter { it.status == ToDoStatus.NEW }
    }

    fun getDoneTodos(): List<TodoDetails> {
        return todoList.filter { it.status == ToDoStatus.DONE }
    }

    fun addTodo(todo: TodoDetails) {
        todoList.add(todo)
    }

    fun deleteTodo(id: Int) {
        todoList.removeAll { it.id == id }
    }

    fun updateTodo(updated: TodoDetails) {
        val index = todoList.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            todoList[index] = updated
        }
    }

    fun markAsDone(id: Int) {
        val index = todoList.indexOfFirst { it.id == id }
        if (index != -1) {
            todoList[index] = todoList[index].copy(status = ToDoStatus.DONE)
        }
    }

//    fun markAsNew(id: Int) {
//        val index = todoList.indexOfFirst { it.id == id }
//        if (index != -1) {
//            todoList[index] = todoList[index].copy(status = ToDoStatus.NEW)
//        }
//    }
}