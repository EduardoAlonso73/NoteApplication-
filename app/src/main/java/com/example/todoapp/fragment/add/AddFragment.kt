package com.example.todoapp.fragment.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentAddBinding
import com.example.todoapp.utils.onItemSelected
import com.example.todoapp.utils.parsePriority
import com.example.todoapp.utils.verifyDataFromUser


class AddFragment : Fragment() {
    private  var _binding: FragmentAddBinding?=null
    private  val binding get()= _binding!!
    private  val mToDoViewModel:ToDoViewModel  by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        binding.priortiesSpinner.onItemSelected()
        return binding.root


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add)  insertDataToDb()
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle=binding.edTitle.text.toString()
        val mPriority=binding.priortiesSpinner.selectedItem.toString()
        val mDescription=binding.etDescription.text.toString()
        val validation=verifyDataFromUser(mTitle, mDescription )
        if(validation){
            val newData=ToDoData(0,mTitle,parsePriority(mPriority), mDescription)
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please full out all fields added!", Toast.LENGTH_SHORT).show()
        }


    }


}

















