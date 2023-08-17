package com.example.hw1.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw1.R
import com.example.hw1.core.BaseFragment
import com.example.hw1.databinding.FragmentTaskBinding

class TaskFragment : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {
    override fun inflateViewBinding(): FragmentTaskBinding {
        return FragmentTaskBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnSave.setOnClickListener {
            val result = TaskModel(binding.etTitle.text.toString())
            viewModel.addTask(result)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, TaskFragment()).commit()
        }

    }
}