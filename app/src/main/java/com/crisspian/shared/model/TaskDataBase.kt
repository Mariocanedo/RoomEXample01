package com.crisspian.shared.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase: RoomDatabase() {
    // Aca se hace un metodo abstracto para acceder al dao.
    abstract fun getTaskDao() : TaskDao

    companion object {
        @Volatile
        private var INSTANCE : TaskDataBase? = null

        fun getDataBase(context: Context): TaskDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    TaskDataBase::class.java,
                        "taskDB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}