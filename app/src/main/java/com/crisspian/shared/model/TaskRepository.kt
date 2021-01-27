package com.crisspian.shared.model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao : TaskDao) {

    // Este value va a contener todos los datos desde la BBDD
    val listAllTask : LiveData<List<Task>> = taskDao.getAllTask()

    // Función que insertara una tarea el la bbdd
    suspend fun insertTask(task : Task) {
        taskDao.createTask(task)
    }

    // función que eliminara una tarea
    suspend fun deleteTask(task : Task){
        taskDao.deleteTask(task)
    }

    //funcion que los elimine todas las tareas
    suspend fun deleteAllTask() {
        taskDao.deleteAllTask()
    }

}