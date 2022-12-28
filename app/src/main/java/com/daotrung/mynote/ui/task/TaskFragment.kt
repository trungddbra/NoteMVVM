package com.daotrung.mynote.ui.task

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daotrung.mynote.R
import com.daotrung.mynote.databinding.FragmentTaskBinding
import com.daotrung.mynote.ui.adapter.RowAdapter
import com.daotrung.mynote.viewmodel.TaskViewModel

class TaskFragment : Fragment() {
    private val viewModel : TaskViewModel by viewModels()
    private var _binding: FragmentTaskBinding? = null

    private val adapter by lazy {
        RowAdapter()
    }
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater,container,false)

        viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.apply {
            recyclerView.adapter = adapter
            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_taskFragment_to_addFragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }

