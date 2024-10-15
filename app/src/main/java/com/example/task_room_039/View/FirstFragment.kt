package com.example.task_room_039.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_room_039.R
import com.example.task_room_039.ViewModel.TaskAdapter
import com.example.task_room_039.ViewModel.TaskViewModel
import com.example.task_room_039.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel : TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // instanciamos el adapater y le pasamos AL rv

         val adapter = TaskAdapter()
         binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)


        // observar el objeto expuesto.

        viewModel.allTask.observe( viewLifecycleOwner, Observer {

            it?.let {

                adapter.update(it)
            }


        })




       binding.fab.setOnClickListener {

           findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
       }


        adapter.selectedItem().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("ITEM SELECTED", it.title)
                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }
        })




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}