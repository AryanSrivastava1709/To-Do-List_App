package com.example.to_do_listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to_do_listapp.databinding.ActivityMainBinding
import com.example.to_do_listapp.db.ToDo
import com.example.to_do_listapp.db.ToDoListDataBase
import java.util.Date
import kotlin.concurrent.thread


//Add ViewBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ToDoListDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Create a random To-Do and push this in the database.
        thread {
            database =
                Room.databaseBuilder(this@MainActivity, ToDoListDataBase::class.java, "todolistDB")
                    .build()
            val todo = ToDo(
                title = "Randomtodotitle",
                desc = "randomtododesc",
                date = Date(System.currentTimeMillis())
            )
            database.todoDao().insert(todo)
        }
    }
}