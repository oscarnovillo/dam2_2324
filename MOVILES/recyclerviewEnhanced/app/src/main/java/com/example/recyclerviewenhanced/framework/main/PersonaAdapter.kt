package com.example.hiltmenu.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewenhanced.R
import com.example.recyclerviewenhanced.databinding.ViewPersonaBinding
import com.example.recyclerviewenhanced.domain.Persona
import com.example.recyclerviewenhanced.framework.main.SwipeGesture
import java.util.*


class PersonaAdapter(
    val context: Context,
    val actions: PersonaActions
) :
    ListAdapter<Persona, PersonaAdapter.ItemViewholder>(DiffCallback()) {

    interface PersonaActions {
        fun onDelete(persona: Persona)
        fun onStartSelectMode()
        fun itemHasClicked(persona:Persona)
        fun isItemSelected(persona:Persona) : Boolean


    }

    private var selectedItem = mutableListOf<Persona>()

    fun resetSelectMode() {
        selectedMode = false

        notifyDataSetChanged()
    }

    fun getSelectedItems() = selectedItem.toList()

    private var selectedMode: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {

        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_persona, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewPersonaBinding.bind(itemView)

        fun bind(item: Persona) {

            itemView.setOnLongClickListener {
                if (!selectedMode) {
                    selectedMode = true
                    actions.onStartSelectMode()
                    item.isSelected = true
                    binding.selected.isChecked = true
                    selectedItem.add(item)
                    notifyDataSetChanged()
                    //notifyItemChanged(adapterPosition)
                }
                true
            }
            with(binding) {
                selected.setOnClickListener {
                    if (selectedMode) {

                        if (binding.selected.isChecked ) {
                            item.isSelected = true
                            itemView.setBackgroundColor(Color.GREEN)
                            //binding.selected.isChecked = true
                            //notifyItemChanged(adapterPosition)
                            selectedItem.add(item)
                        } else {
                            item.isSelected = false
                            itemView.setBackgroundColor(Color.WHITE)
                            selectedItem.remove(item)
                            //binding.selected.isChecked = false
                            //notifyItemChanged(adapterPosition)

                        }
                        actions.itemHasClicked(item)
                    }
                }

                tvNombre.text = item.nombre
                tvId.text = item.id.toString()
                if (selectedMode)
                    selected.visibility = View.VISIBLE
                else{
                    item.isSelected = false
                    selected.visibility = View.GONE
                }

                if (actions.isItemSelected(item)) {
                    itemView.setBackgroundColor(Color.GREEN)
                    binding.selected.isChecked = true
                    //selected.visibility = View.VISIBLE
                } else {
                    itemView.setBackgroundColor(Color.WHITE)
                    binding.selected.isChecked = false
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Persona>() {
        override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem == newItem
        }
    }

    val swipeGesture = object : SwipeGesture(context) {
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            var initPos = viewHolder.adapterPosition
//            var targetPos = target.adapterPosition
//
//            val mutable = currentList.toMutableList()
//            Collections.swap(mutable,initPos,targetPos)
//
//           // this@PersonaAdapter.submitList(mutable)
//
//            return false
//
//        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //if (!selectedMode) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        selectedItem.remove(currentList[viewHolder.adapterPosition])
                        actions.onDelete(currentList[viewHolder.adapterPosition])
                        if (selectedMode)
                            actions.itemHasClicked(currentList[viewHolder.adapterPosition])
                    }
                }
            //}
        }
    }


}


