package com.example.todoapp.fragment.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.data.models.ToDoData

class ToDoDiffUtil(private  val oldList:List<ToDoData>,private  val newList:List<ToDoData>):DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size
    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
     return oldList[oldItemPosition]=== newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].description == newList[newItemPosition].description &&
                oldList[oldItemPosition].priority== newList[newItemPosition].priority
    }
}