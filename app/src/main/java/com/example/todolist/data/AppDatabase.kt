package com.example.todolist.data
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.models.TodoDetails

@Database(
    entities = [TodoDetails::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}