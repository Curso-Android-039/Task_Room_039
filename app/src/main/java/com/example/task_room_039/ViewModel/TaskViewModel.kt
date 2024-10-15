package com.example.task_room_039.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.task_room_039.Modelo.Task
import com.example.task_room_039.Modelo.TaskDatabase
import com.example.task_room_039.Modelo.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application : Application) :AndroidViewModel(application){


    private val repository: TaskRepository

    // lista de tareas
    val allTask : LiveData<List<Task>>

    init {

        val taskDao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }


    // INSERT
    fun insertTask(task: Task) = viewModelScope.launch {

        repository.insertTask(task)
    }





}


