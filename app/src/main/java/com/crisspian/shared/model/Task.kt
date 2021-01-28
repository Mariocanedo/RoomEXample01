package com.crisspian.shared.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
                @PrimaryKey(autoGenerate = true)
                @NonNull
                var id: Int = 0,
                var title:String ,
                var taskDescription: String,
                val date: String,
                val priority: Int,
                val state: Boolean)
