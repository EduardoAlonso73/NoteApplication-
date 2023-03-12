package com.example.todoapp

import android.icu.text.ConstrainedFieldPosition
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.fragment.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.invoke.ConstantCallSite

class BindingAdapters {
    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view:FloatingActionButton,navigate:Boolean){
            view.setOnClickListener {
                if(navigate){
                    it.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }
        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view:Spinner,priority:Priority){
            when(priority){
                Priority.HIGH-> view.setSelection(0)
                Priority.MEDIUM-> view.setSelection(1)
                Priority.LOW-> view.setSelection(2)
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView:CardView,priority:Priority){
            when(priority){
                Priority.HIGH->{cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,R.color.red))}
                Priority.MEDIUM->{cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,R.color.yellow))}
                Priority.LOW->{cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context,R.color.green))}
            }
        }


        @BindingAdapter("android:sentDataToUpdateFragment")
        @JvmStatic
        fun sentDataToUpdateFragment(view:ConstraintLayout,currentItem:ToDoData){
            view.setOnClickListener {
                val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)

            }
        }
    }
}