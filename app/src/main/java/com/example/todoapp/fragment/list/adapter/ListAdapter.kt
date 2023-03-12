package com.example.todoapp.fragment.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.models.ToDoData
import com.example.todoapp.databinding.RowLayoutBinding
import com.example.todoapp.utils.OnListener

class ListAdapter() : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

      var dataLis= emptyList<ToDoData>()
    private lateinit var context: Context


    /* ═-═-═-═-═-═-═-═- Member Implmetatio -═-═-═-═--═-══-═-═-═*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        context=parent.context
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData=dataLis[position]
        holder.setListener(itemData)
           /* with(binding){

                tvTitle.text=itemData.title
                tvDescription.text=itemData.description
                when(itemData.priority){
                    Priority.HIGH->priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context,R.color.red))
                    Priority.LOW->priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context,R.color.yellow))
                    Priority.MEDIUM->priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context,R.color.green))

                }

            }*/
    }
    override fun getItemCount(): Int =dataLis.size

    /* ═-═-═-═-═-═-═-═- My methods -═-═-═-═--═-══-═-═-═*/
    fun setData(data:List<ToDoData>){
        val toDoDiffUtil=ToDoDiffUtil(dataLis,data)
        val toDoDiffUtilResult=DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataLis=data
        toDoDiffUtilResult.dispatchUpdatesTo(this)
       // notifyItemChanged(dataLis.size-1)
       //notifyDataSetChanged()
    }





/* ═-═-═-═-═-═-═-═- ViewHolder -═-═-═-═--═-══-═-═-═*/

    inner  class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding=RowLayoutBinding.bind(view)
        fun setListener(itemData: ToDoData ){
           // binding.root.setOnClickListener { listener.onClickListener(itemData) }
           // binding.cbDelete.setOnClickListener{ listener.onLongClick(itemEntity) }
            binding.toDoData=itemData
            binding.executePendingBindings()
        }
    }

}


