package com.crisspian.shared

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.shared.databinding.FragmentFirstBinding
import com.crisspian.shared.model.Task


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : TaskViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instanciamos el adapter y se lo pasamos al RV
        val adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        val task = Task(1,
                "Mi primera tarea",
                "Esta es una tarea de prueba",
                "27-01-2021",
                2,
                false)

        val task2 = Task(2,
                "Mi Segunda tarea",
                "Esta es una tarea de prueba dos",
                "28-01-2021",
                1,
                false)

        val task3 = Task(3,
                "Mi tercera tarea",
                "Estsdsds",
                "28-01-2021",
                1,
                true)

        viewModel.insertTask(task3)

        // Esto esta observando al objeto expuesto en el viewModel
        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })

    }
}