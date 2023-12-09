package com.example.navigationdecero.ui.pantallas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdecero.R
import com.example.navigationdecero.databinding.ItemHolderBinding

class MiAdapter(val miActions: MiActions) :
    ListAdapter<String,MiViewHolder>(DiffCallback()) {


    interface MiActions {
        fun itemHasClicked(cadena: String)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        return MiViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_holder, parent, false),
            miActions::itemHasClicked
        )
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }
}

class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

data class Dato(val cadena : String)

class MiViewHolder (itemView: View,
    val onClick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemHolderBinding.bind(itemView)

    fun bind(item: String) {
        binding.textView.text = item
        itemView.setOnClickListener {
            onClick(item)
        }
    }

}
