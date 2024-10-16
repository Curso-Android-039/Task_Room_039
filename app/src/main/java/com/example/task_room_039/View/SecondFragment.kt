package com.example.task_room_039.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.task_room_039.Modelo.Task
import com.example.task_room_039.R
import com.example.task_room_039.ViewModel.TaskViewModel
import com.example.task_room_039.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TaskViewModel by activityViewModels()
    private var idTask : Int =0
    private var taskSelected : Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observar la tarea seleccionada desde el ViewModel

       viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
           it?.let {

              // si hay una tarea seleccionada desde el viewModel

               binding.etTitle.setText(it.title)
               binding.etDescription.setText(it.taskDescription)
               binding.etDate.setText(it.date)
               binding.etPriority.setText(it.priority)
               binding.cbStatenew.isChecked = it.state
               idTask = it.id
               taskSelected = it

           }


       })

      binding.btnsave.setOnClickListener{

          if( idTask ==0){
              saveNewTask()
          }
          else{

              updateTask()
          }


      }






    }




     // funcion para guardar una nueva tarea

    private fun saveNewTask(){

        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val date = binding.etDate.text.toString()
        val priority = binding.etPriority.text.toString().toInt()
        val state = binding.cbStatenew.isChecked


        // verificar que los campos esten llenos

        if(title.isEmpty() || description.isEmpty()  || date.isEmpty()){


            Toast.makeText(context,"debes añadir todos los datos",
                Toast.LENGTH_LONG).show()
            return
        }

        // crear una nueva tarea e insertamos con el viewModel

        val newTask = Task(
            title = title,
            taskDescription = description,
            date = date,
            priority = priority,
            state = state
        )

    viewModel.insertTask(newTask)
        Toast.makeText(context,"Tarea añadida exitosamente",
            Toast.LENGTH_LONG).show()
        navigateToFirstFragment()


    }

    private fun updateTask(){

        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val date = binding.etDate.text.toString()
        val priority = binding.etPriority.text.toString().toInt()
        val state = binding.cbStatenew.isChecked


        // verificar que los campos esten llenos

        if(title.isEmpty() || description.isEmpty()  || date.isEmpty()){


            Toast.makeText(context,"debes añadir todos los datos",
                Toast.LENGTH_LONG).show()
            return
        }

        val updateTask = Task(
            title = title,
            taskDescription = description,
            date = date,
            priority = priority,
            state = state
        )

        viewModel.updateTask(updateTask)

        Toast.makeText(context,"Tarea Actualizada Exitosamente",
            Toast.LENGTH_LONG).show()
        navigateToFirstFragment()

    }




    private fun navigateToFirstFragment() {
        viewModel.selected(null)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}