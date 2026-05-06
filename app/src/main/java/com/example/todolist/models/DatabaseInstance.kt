package com.example.todolist.models
import android.content.Context
import androidx.room.Room
import com.example.todolist.data.AppDatabase

object DatabaseInstance {

    private var db: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "todo_db"
            ).allowMainThreadQueries() // simple for now
                .build()
        }

        return db!!
    }
}