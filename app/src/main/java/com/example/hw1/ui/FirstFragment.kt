package com.example.hw1.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw1.R
import com.example.hw1.core.BaseFragment
import com.example.hw1.databinding.FragmentFirstBinding
import com.example.hw1.ui.home.adapter.TaskAdapter
import com.example.hw1.ui.task.TaskFragment
import com.example.hw1.ui.task.TaskModel

class FirstFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {

    private val adapter by lazy { TaskAdapter(this::deleteClick, this::clickCheckBox) }

    private fun clickCheckBox(task: TaskModel) {
        viewModel.updateTask(task)
        setData()
    }

    override fun inflateViewBinding(): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(layoutInflater)
    }

    private fun deleteClick(task: TaskModel) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteTask(task)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    override fun initView() {
        binding.recyclerView.adapter = adapter
        binding.btnAddTasks.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, TaskFragment()).commit()
        }
        setData()

    }

    private fun setData() {
        viewModel.list.observe(requireActivity()) {
            binding.recyclerView.adapter = adapter
            adapter.addData(it)
        }
    }
}

}