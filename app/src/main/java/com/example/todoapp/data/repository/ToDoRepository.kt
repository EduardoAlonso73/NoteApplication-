package com.example.todoapp.data.repository

import android.icu.text.StringSearch
import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData:LiveData<List<ToDoData>> =toDoDao.getAllData()
    val sorByHighPriority:LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sorByLowPriority:LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    suspend fun insertData(data: ToDoData){
        toDoDao.insetData(data)
    }
    suspend fun updateData(data: ToDoData){
        toDoDao.updateData(data)
    }
    suspend fun deleteData(data: ToDoData){
        toDoDao.deleteData(data)
    }
    suspend fun deleteAll(){
        toDoDao.deteleAll()
    }
    fun searchNote(search: String):LiveData<List<ToDoData>> = toDoDao.searchNote(search)
}