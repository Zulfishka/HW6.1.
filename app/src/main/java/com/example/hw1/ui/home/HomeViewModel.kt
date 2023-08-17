package com.example.hw1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1.ui.task.TaskModel

class HomeViewModel : ViewModel() {

    private val _list = MutableLiveData<MutableList<TaskModel>>()

    val list: LiveData<MutableList<TaskModel>>
        get() = _list
    val listTask = mutableListOf<TaskModel>()

    fun addTask(lists: TaskModel){
        listTask.add(lists)
        _list.value = listTask
    }

    fun deleteTask(task: TaskModel) {
        listTask.remove(task)
        _list.value = listTask
    }

    fun updateTask(taskModel: TaskModel) {
        val updatedTaskModel = taskModel.copy(isCompleted = taskModel.isCompleted)
        listTask.add(updatedTaskModel)
        //listTask.distinctBy { it.check }
        listTask.toSet()
        _list.value = listTask
    }
}