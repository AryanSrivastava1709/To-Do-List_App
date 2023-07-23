package com.example.to_do_listapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// DataBase Imp. points
// 1.Entity = Table Structure = How your data is stored

@Entity(tableName = "ToDo_Table")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val isMarkedDone: Boolean,

    val title: String,

    val desc: String,

    val date: Date
)


