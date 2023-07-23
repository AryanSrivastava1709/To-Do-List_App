package com.example.to_do_listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to_do_listapp.databinding.ActivityMainBinding
import com.example.to_do_listapp.databinding.BottomSheetBinding
import com.example.to_do_listapp.db.ToDo
import com.example.to_do_listapp.db.ToDoListDataBase
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date
import kotlin.concurrent.thread


//Add ViewBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: ToDoListDataBase
    private lateinit var adapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ToDoAdapter(mutableListOf())
        binding.rvTODOList.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,false)
        binding.rvTODOList.adapter = adapter

        binding.fabAddToDo.setOnClickListener{
            showBottomSheet()
        }
        //Create a random To-Do and push this in the database.
        thread {
            database = Room.databaseBuilder(this@MainActivity, ToDoListDataBase::class.java, "todolistDB").build()

            val listOfTodos = database.todoDao().fetchData()
            adapter.updateData(listOfTodos)
        }
    }

    private fun showBottomSheet(){
        val bottomSheet = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(bottomSheet.root)

        bottomSheet.btn.setOnClickListener {
            //Add To-Do
            if(bottomSheet.tietTitle.text.isNullOrBlank()){
                bottomSheet.tielTitle.error = "Cannot be Empty"
                return@setOnClickListener
            }
            if(bottomSheet.tietDesc.text.isNullOrBlank()){
                bottomSheet.tielDesc.error = "Cannot be Empty"
                return@setOnClickListener
            }
            val todo = ToDo(
                title = bottomSheet.tietTitle.text.toString(),
                desc = bottomSheet.tietDesc.text.toString(),
                date = Date(System.currentTimeMillis())
            )
            adapter.addNewItem(todo)
            thread{
                database.todoDao().insert(todo)

            }
            dialog.dismiss()
        }

        dialog.show()
    }
}