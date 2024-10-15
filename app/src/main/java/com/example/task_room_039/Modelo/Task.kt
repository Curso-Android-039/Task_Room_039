package com.example.task_room_039.Modelo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


// 1. crear data class con sus atributos
// 2 agregar anotaciòn @Entity y agregar el nombre de la tabla
// 3. agregar PK especificar que es auto generada y no puede tener valor null

@Entity(tableName = "task_table")
data class Task (


    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id : Int = 0,
    var title : String,
    var taskDescription : String,
    var date : String,
    var priority : Int,
    var state : Boolean




)