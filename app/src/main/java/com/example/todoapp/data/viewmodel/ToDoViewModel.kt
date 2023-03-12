package com.example.todoapp.data.viewmodel

import android.app.Application
import android.icu.text.StringSearch
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.ToDoDatabase
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application:Application):AndroidViewModel(application) {

    private  val toDoDao= ToDoDatabase.getDatabase(application).toDoDao()
    private  val repository:ToDoRepository by  lazy { ToDoRepository(toDoDao) }
    val getAllData:LiveData<List<ToDoData>> by lazy { repository.getAllData }
    val sortByHighPriority:LiveData<List<ToDoData>> by lazy { repository.sorByHighPriority }
    val sortByLowPriority:LiveData<List<ToDoData>> by lazy { repository.sorByLowPriority }




    fun insertData(data: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data)
        }
    }
    fun updateData(data: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(data)
        }
    }
    fun deteleData(data: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(data)
        }
    }

    fun deteleAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
    fun searchNote(search: String):LiveData<List<ToDoData>> = repository.searchNote(search)


}






















