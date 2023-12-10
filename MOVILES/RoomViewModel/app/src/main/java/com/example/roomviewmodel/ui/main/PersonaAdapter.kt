package com.example.roomviewmodel.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomviewmodel.R
import com.example.roomviewmodel.databinding.ViewPersonaBinding
import com.example.roomviewmodel.domain.modelo.Persona

class PersonaAdapter:
    ListAdapter<Persona, PersonaAdapter.ItemViewholder>(DiffCallback())  {

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


    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewPersonaBinding.bind(itemView)

        fun bind(item: Persona) = with(binding) {
            tvNombre.text = item.nombre
            tvId.text = item.id.toString()
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
}
