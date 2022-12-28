package com.daotrung.mynote.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.daotrung.mynote.R
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daotrung.mynote.database.TaskEntry
import com.daotrung.mynote.databinding.FragmentUpdateBinding
import com.daotrung.mynote.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val viewModel : TaskViewModel by viewModels()
    private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        val args = UpdateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            updateEdtTask.setText(args.taskEntry.title)
            updateSpinner.setSelection(args.taskEntry.priority)

            btnUpdate.setOnClickListener {
                if(TextUtils.isEmpty(updateEdtTask.text)){
                    Toast.makeText(requireContext(),"It is empty",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val task_str = updateEdtTask.text
            val priority = updateSpinner.selectedItemPosition

            val taskEntry = TaskEntry(
                args.taskEntry.id,
                task_str.toString(),
                priority,
                args.taskEntry.timestamp
            )

            viewModel.update(taskEntry)
            Toast.makeText(requireContext(),"Updated!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_taskFragment)

        }
        return binding.root
    }

}