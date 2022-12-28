package com.daotrung.mynote.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daotrung.mynote.R
import com.daotrung.mynote.database.TaskEntry
import com.daotrung.mynote.databinding.FragmentAddBinding
import com.daotrung.mynote.databinding.FragmentTaskBinding
import com.daotrung.mynote.viewmodel.TaskViewModel


class AddFragment : Fragment() {

    private val viewModel : TaskViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)
        )

        binding.apply {
            spinner.adapter = myAdapter
            btnAdd.setOnClickListener {
                if(TextUtils.isEmpty(edtTask.text)){
                    Toast.makeText(requireContext(),"It is empty",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title_str = edtTask.text.toString()
                val priority = spinner.selectedItemPosition

                val taskEntry = TaskEntry(
                    0,title_str,priority,System.currentTimeMillis()
                )
                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_taskFragment)


            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}