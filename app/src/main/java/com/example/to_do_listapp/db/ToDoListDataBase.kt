package com.example.to_do_listapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ToDo::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ToDoListDataBase: RoomDatabase() {
    abstract fun todoDao():ToDoDAO
}