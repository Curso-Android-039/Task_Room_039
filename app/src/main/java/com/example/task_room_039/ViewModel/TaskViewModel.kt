package com.example.task_room_039.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    // INSERT corrutinas se esta ejecutando en un hilo secundario
    fun insertTask(task: Task) = viewModelScope.launch {

        repository.insertTask(task)
    }


    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    // eliminar todo
    fun deleteAllTask() = viewModelScope.launch {
        repository.deleteAllTask()
    }


    // recibe el objeto seleccionado lo envuelve en liveDATA para poder observarlo

    private var selectedTask : MutableLiveData<Task> = MutableLiveData()


    // va a servir cuando selecciono una tarea y la envia
    fun selected(task: Task){
        selectedTask.value = task

    }

    // para recibir el objeto seleccionado
    fun selectedItem(): LiveData<Task> = selectedTask







}


