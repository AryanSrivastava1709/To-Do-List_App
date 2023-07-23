package com.example.to_do_listapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


// 2. DAO = Data Access Object = This helps you in accessing the db without writing lot of code.

@Dao
interface ToDoDAO {
    //CRUD = Create,Read,Update and Delete operations is maneged by Dao objects.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: ToDo)

    @Query("select * from ToDo_Table")
    fun fetchData(): List<ToDo>
}