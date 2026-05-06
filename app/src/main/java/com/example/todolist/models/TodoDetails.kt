package com.example.todolist.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoDetails(
    @PrimaryKey(autoGenerate = true)
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