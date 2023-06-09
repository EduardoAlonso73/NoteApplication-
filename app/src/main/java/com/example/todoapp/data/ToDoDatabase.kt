package com.example.todoapp.data

import android.content.Context
import androidx.room.*
import com.example.todoapp.data.models.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(contex: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    contex.applicationContext,
                    ToDoDatabase::class.java,
                    "db_todo"
                ).build()
                return instance
            }

        }
    }


}

