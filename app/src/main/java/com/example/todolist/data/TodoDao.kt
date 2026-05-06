package com.example.todolist.data
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.models.TodoDetails

@Dao
interface TodoDao {

    @Insert
    fun insert(todo: TodoDetails)

    @Update
    fun update(todo: TodoDetails)

    @Delete
    fun delete(todo: TodoDetails)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAll(): LiveData<List<TodoDetails>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getById(id: Int): TodoDetails
}