package com.example.flows.framework.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flows.R
import com.example.flows.databinding.MovieViewBinding
import com.example.flows.domain.modelo.Movie

class MovieAdapter() : ListAdapter<Movie, MovieAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {

        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = MovieViewBinding.bind(itemView)

        fun bind(item: Movie) {
            binding.textTitulo.setText(item.titulo)

        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.titulo == newItem.titulo
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}