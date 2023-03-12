package com.example.todoapp.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority



fun Spinner.onItemSelected(){
    val context:Context  by lazy { this.context}
    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context,R.color.red))}
                1->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context, R.color.yellow))}
                2->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context, R.color.green))}
            }
        }
    }
    this.onItemSelectedListener= listener
}

fun verifyDataFromUser(title: String, description: String): Boolean {
    return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) false
    else (title.isNotEmpty() || description.isNotEmpty())
}


fun parsePriorityToInt(priority: Priority):Int=when(priority){
    Priority.HIGH -> 0
    Priority.MEDIUM -> 1
    Priority.LOW -> 2
}


fun parsePriority(priority: String): Priority {
    return when (priority) {
        "High Priority" -> {
            Priority.HIGH
        }
        "Medium Priority" -> {
            Priority.MEDIUM
        }
        "Low Priority" -> {
            Priority.LOW
        }
        else -> Priority.LOW

    }
}
