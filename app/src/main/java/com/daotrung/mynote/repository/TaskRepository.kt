package com.daotrung.mynote.repository

import androidx.lifecycle.LiveData
import com.daotrung.mynote.database.TaskDao
import com.daotrung.mynote.database.TaskEntry

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insert(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    suspend fun update(taskEntry: TaskEntry) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
    fun getAllTasks(): LiveData<List<TaskEntry>> = taskDao.getAllTask()
}