package com.example.to_do_listapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_listapp.databinding.TodoListItemBinding
import com.example.to_do_listapp.db.ToDo

/*TODO 4: Adding a ViewHolder*/
class ToDoListViewHolder(private val itemBinding: TodoListItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindData(todo: ToDo){
        itemBinding.itemToDo.isChecked = todo.isMarkedDone
        itemBinding.itemToDoTitle.text = todo.title
        itemBinding.itemToDoDesc.text = todo.desc
        itemBinding.itemToDoDate.text = todo.date.toString()
    }
}

/*TODO : Adding a Adapter*/

class ToDoAdapter(
    private var listOfTodos: MutableList<ToDo>
): RecyclerView.Adapter<ToDoListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(TodoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount() = listOfTodos.size

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.bindData(listOfTodos[position])
    }

    fun updateData(newList: MutableList<ToDo>){
        listOfTodos = newList
        notifyDataSetChanged()
    }

    fun addNewItem(todo: ToDo){
        listOfTodos.add(0,todo)
        notifyItemInserted(0)
    }
}

/*TODO 5: Add a click handle event*/
interface ToDoStateChangeListener{
    fun onCheckStateChanged(position: Int)
}