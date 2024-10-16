package com.example.task_room_039.Modelo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {


    // INSERTAR una tarea

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask (task: Task)


    // INSERTAR UN LISTADO DE TAREAS
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertAll(taskList : List<Task>)


    // ACTUALIZAR
    @Update
    suspend fun  updateTask(task: Task)


    //Eliminar una tarea
    @Delete
    suspend fun  deleteTask( task: Task)


    // ELIMINAR TODO
    @Query("DELETE FROM task_table")
    suspend fun  deleteAllTask()

  // Eliminar por id ?
    @Query("Delete from task_table   Where id= :mId")
    suspend fun DeleteTaskById(mId:Int)




  // SELECT TAREA POR ID
    // liveData no es compatible con el uso de funciones suspendidas
    @Query("SELECT * FROM task_table where id = :mId")
   fun getTaskById(mId:Int): LiveData<Task>


    // obtiene todas las tareas
    @Query("SELECT * FROM task_table")
    fun getAllTask(): LiveData<List<Task>>




}