package com.example.task_room_039.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.task_room_039.Modelo.Task
import com.example.task_room_039.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>() {


    // referencia a la data
    private var  mlistTask = listOf<Task>()

    // para escuchar cuandos se selecciona una tarea
    private val seletedTask = MutableLiveData<Task>()

    // para pasar de con un dato o una seleccion a otra pantalla
    fun selectedItem(): LiveData<Task> = seletedTask


    // actualizaciòn auto

    fun update (listTask : List<Task>){

        mlistTask = listTask
       // falta en notify
        notifyDataSetChanged()
    }





    inner class  TaskVH(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener{

       fun bind( task: Task){
           binding.tvTitle.text = task.title
           binding.tvDescription.text = task.taskDescription
           binding.tvDate.text = task.date
           binding.cbState.isChecked = task.state
           itemView.setOnClickListener(this)

       }



        override fun onClick(p0: View?) {
          seletedTask.value = mlistTask[adapterPosition]
        }


    }

    // inflado de la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
      return  TaskVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = mlistTask.size

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
     val task = mlistTask [position]
        holder.bind(task)


    }


}