package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.models.ToDoData

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insetData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData):Int

    @Delete
    suspend fun deleteData(toDoData: ToDoData):Int

    @Query("DELETE FROM todo_table")
    suspend fun deteleAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchNote(searchQuery:String):LiveData<List<ToDoData>>


    @Query("""SELECT * FROM todo_table ORDER BY CASE 
            WHEN priority LIKE 'H%' THEN 1 
            WHEN priority LIKE 'M%'THEN 2
            WHEN priority LIKE 'L%' THEN 3 END""")
    fun sortByHighPriority():LiveData<List<ToDoData>>

    @Query("""SELECT * FROM todo_table ORDER BY CASE 
            WHEN priority LIKE 'L%' THEN 1 
            WHEN priority LIKE 'M%'THEN 2
            WHEN priority LIKE 'H%' THEN 3 END""")
    fun sortByLowPriority():LiveData<List<ToDoData>>
}