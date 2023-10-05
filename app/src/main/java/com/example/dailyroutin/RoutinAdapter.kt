package com.example.dailyroutin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyroutin.database.DatabaseHelper
import com.example.dailyroutin.database.DatabaseManager
import com.example.dailyroutin.databinding.RotinItemBinding

class RoutinAdapter(
    var todo: List<Todo>
) : RecyclerView.Adapter<RoutinAdapter.RoutinViewHolder>() {

    private var listener: OnClickListenerTodo? = null

    private lateinit var binding: RotinItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutinViewHolder {

        binding = RotinItemBinding.inflate(LayoutInflater.from(parent.context))

        return RoutinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutinViewHolder, position: Int) {

        holder.itemView.apply {


            binding.textTodo.text = todo[position].title
            binding.checkBoxTodo.isChecked = todo[position].isChecked
            binding.textDetail.text=todo[position].detail

            binding.imgView.setOnClickListener {
                if (listener != null) {
                    listener!!.onClick(todo[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return todo.size
    }

    inner class RoutinViewHolder(itemview: RotinItemBinding) :
        RecyclerView.ViewHolder(itemview.root)


    fun setOnClick(onClickListener: OnClickListenerTodo) {
        this.listener = onClickListener
    }
}


interface OnClickListenerTodo {
    fun onClick(todo: Todo)
}