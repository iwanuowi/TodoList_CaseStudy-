package com.example.todolist.models

data class TodoDetails(
    val id: Int,
    val title: String,
    val meaning: String? = null,
    val synonyms: String? = null,
    val details: String,
    val status: ToDoStatus

)

enum class ToDoStatus{
    NEW,
    DONE
}