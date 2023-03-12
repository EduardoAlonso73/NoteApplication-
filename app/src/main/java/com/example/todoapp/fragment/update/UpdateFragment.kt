package com.example.todoapp.fragment.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.utils.onItemSelected
import com.example.todoapp.utils.parsePriority
import com.example.todoapp.utils.parsePriorityToInt
import com.example.todoapp.utils.verifyDataFromUser
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class UpdateFragment : Fragment() {
    private  var _binding: FragmentUpdateBinding?=null
    private val binding get()=_binding!!
    private  val mToDoViewModel: ToDoViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        _binding =FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args=args
        //setupUIData()
        binding.priortiesCurrentSpinner.onItemSelected()
        return  binding.root
    }

    /*private fun setupUIData() {
        with(binding){
            edCurrentTitle.setText(args.currentItem.title)
            etCurrentDescription.setText(args.currentItem.description)
            priortiesCurrentSpinner.setSelection(parsePriorityToInt(args.currentItem.priority))

        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> { updateItem()}
            R.id.menu_delete-> {confirmItemRemove()}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemove() {
        MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_alert)
            .setTitle(R.string.note_dialog_delete_title)
            .setMessage(R.string.note_dialog_delete_single_msg)
            .setNegativeButton(R.string.dialog_cancel,null)
            .setPositiveButton(R.string.note_dialog_delete_confirm) { _, _ ->
                mToDoViewModel.deteleData(args.currentItem)
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            .show()


    }

    private fun updateItem() {
        with(binding){
            val mTitle= edCurrentTitle.text.toString()
            val mPriority=priortiesCurrentSpinner.selectedItem.toString()
            val mDescription=etCurrentDescription.text.toString()
            val validation= verifyDataFromUser(mTitle, mDescription )

            if(validation){
               /* val newData= ToDoData(args.currentItem.id,mTitle,parsePriority(mPriority), mDescription)
                mToDoViewModel.updateData(newData)*/
                val newData= args?.currentItem?.id?.let { ToDoData(it,mTitle,parsePriority(mPriority), mDescription) }
                mToDoViewModel.updateData(newData!!)
                Toast.makeText(requireContext(), "Successfully upaded!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }else{
                Toast.makeText(requireContext(), "Please full out all fields added!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}