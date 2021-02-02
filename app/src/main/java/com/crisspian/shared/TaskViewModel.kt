package com.crisspian.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.crisspian.shared.model.Task
import com.crisspian.shared.model.TaskDataBase
import com.crisspian.shared.model.TaskRepository
import kotlinx.coroutines.launch

class  TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    //LiveData de tareas
    val allTask : LiveData<List<Task>>

    init {
        Log.i("ViewModel", "Create The ViewModel" )
        val taskDao = TaskDataBase.getDataBase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun deleteAllTask() = viewModelScope.launch {
        repository.deleteAllTask()
    }

    // Recibe el objeto seleccionado y lo convierte en live data para observarDO
    private var selectedTask: MutableLiveData<Task> = MutableLiveData()

    fun selected(task: Task?) {
        selectedTask.value = task
    }

    fun selectedItem(): LiveData<Task> = selectedTask


    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel Destroy")
    }
}