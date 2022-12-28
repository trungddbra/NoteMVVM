package com.daotrung.mynote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.daotrung.mynote.database.TaskDatabase
import com.daotrung.mynote.database.TaskEntry
import com.daotrung.mynote.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application :Application) : AndroidViewModel(application){
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository : TaskRepository = TaskRepository(taskDao)
    val getAllTasks : LiveData<List<TaskEntry>> = repository.getAllTasks()

    fun insert(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(taskEntry)
        }
    }

    fun delete(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(taskEntry)
        }
    }

    fun update(taskEntry: TaskEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(taskEntry)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }




}