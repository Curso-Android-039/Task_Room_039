package com.example.task_room_039.Modelo

import androidx.lifecycle.LiveData

class TaskRepository ( private val taskDao: TaskDao){



    // Este value va a contener todos los datos desde la BBDD

       val listAllTask : LiveData<List<Task>> = taskDao.getAllTask()




    // insert
    suspend fun  insertTask( task: Task){
        taskDao.insertTask(task)
    }


    // delete 1

    suspend fun deleteTask(task: Task){

        taskDao.deleteTask(task)
    }

    // delete All


    suspend fun deleteAllTask(){

        taskDao.deleteAllTask()
    }


    // actualizar
    suspend fun  updateTask(task: Task){

        taskDao.updateTask(task)
    }





}