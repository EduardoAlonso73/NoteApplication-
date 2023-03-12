package com.example.todoapp.fragment.list

import android.content.ClipData.Item
import android.os.Bundle
import android.view.*

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentListBinding
import com.example.todoapp.fragment.list.adapter.ListAdapter
import com.example.todoapp.utils.OnListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class ListFragment():Fragment()/*OnListener*/ , SearchView.OnQueryTextListener{
    private  var _binding: FragmentListBinding?=null
    private  val binding get() =_binding!!
    private  val mAdapter: ListAdapter by lazy { ListAdapter() }
    private val mToDoViewModel:ToDoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupVieModel()
        setupRecyclerView()
        binding.fbAdd.setOnClickListener {findNavController().navigate(R.id.action_listFragment_to_addFragment)}
        return binding.root
    }

    private fun setupVieModel() {
        mToDoViewModel.getAllData.observe(viewLifecycleOwner){data->
           mAdapter.setData(data)
            binding.contentIdNoData.visibility=if(data.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter=mAdapter
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            // layoutManager=LinearLayoutManager(requireContext())
            //layoutManager=GridLayoutManager(requireContext(),2)
            itemAnimator=SlideInUpAnimator().apply {
                addDuration=300
            }
            swipeToDetele(this)
        }

    }
    private  fun swipeToDetele(recyclerView: RecyclerView){
        val swipeToDeleteCallback=object :SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //ITEM TO DELETE
                val deletedItem=mAdapter.dataLis[viewHolder.adapterPosition]
                //DELETE ITEM
                mToDoViewModel.deteleData(deletedItem)
                mAdapter.notifyItemChanged(viewHolder.adapterPosition)
                //RESTORE DELETE ITEM
                restoreDeletedData(viewHolder.itemView,deletedItem,viewHolder.adapterPosition )


            }
        }
        val itemTouchHelper=ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private  fun restoreDeletedData(view: View,deletedItem:ToDoData,position:Int){
        val snackBar=Snackbar.make(
            view,
            "Deleted ${deletedItem.title}",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo"){
            mToDoViewModel.insertData(deletedItem)
           // mAdapter.notifyItemChanged(position)
        }
        snackBar.show()

    }
    private fun confirmItemRemove() {
        MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_alert_red)
            .setTitle(R.string.note_dialog_delete_title)
            .setMessage(R.string.note_dialog_delete_all_msg)
            .setNegativeButton(R.string.dialog_cancel,null)
            .setPositiveButton(R.string.note_dialog_delete_confirm) { _, _ -> mToDoViewModel.deteleAll() }
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> { confirmItemRemove()}
            R.id.menu_priority_high->{mToDoViewModel.sortByHighPriority.observe(this){ mAdapter.setData(it)}}
            R.id.menu_priority_low->{mToDoViewModel.sortByLowPriority.observe(this){ mAdapter.setData(it)}}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
        val search=menu.findItem(R.id.menu_search)
        (search.actionView as? SearchView)?.apply {
            isSubmitButtonEnabled=true
            setOnQueryTextListener(this@ListFragment)
        }
       /* val searchView=search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled=true
        searchView?.setOnQueryTextListener(this)*/
    }

   /* override fun onClickListener(data: ToDoData) {
        val action=ListFragmentDirections.actionListFragmentToUpdateFragment(data)
        findNavController().navigate(action)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchThroughtDb(it)
        }
        return  true
    }
    override fun onQueryTextChange(query: String?): Boolean {
        query?.let {
            searchThroughtDb(it)
        }
        return  true
    }

    private fun searchThroughtDb(query: String) {
       val searchQuery="%$query%"
        mToDoViewModel.searchNote(searchQuery).observe(this){list->
            list?.let { mAdapter.setData(it)}

        }
    }
}




