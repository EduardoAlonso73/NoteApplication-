package com.example.todoapp.utils

import com.example.todoapp.data.models.ToDoData

interface OnListener {
    fun onClickListener(data: ToDoData)
}