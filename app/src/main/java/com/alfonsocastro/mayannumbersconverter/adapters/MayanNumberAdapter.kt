package com.alfonsocastro.mayannumbersconverter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfonsocastro.mayannumbersconverter.databinding.ListItemMayanNumberBinding
import com.squareup.picasso.Picasso

class MayanNumberAdapter(private val inflater: LayoutInflater) :
    androidx.recyclerview.widget.ListAdapter<Char, MayanNumberAdapter.MayanNumberHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MayanNumberHolder {
        val binding = ListItemMayanNumberBinding.inflate(
            inflater,
            parent,
            false)
        return MayanNumberHolder(binding)
    }

    override fun onBindViewHolder(holder: MayanNumberHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MayanNumberHolder(private val _binding: ListItemMayanNumberBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        fun bind(number: Char) {
            val imageFilePath = "file:///android_asset/mayan_numbers/${number.lowercase()}.png"
            // Log.d("MayanNumbersHolder", imageFilePath)
            Picasso.get()
                .load(imageFilePath)
                .into(_binding.mayanNumberImageView)
        }

    }

    private class DiffCallback: DiffUtil.ItemCallback<Char>() {
        override fun areItemsTheSame(oldItem: Char, newItem: Char): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Char, newItem: Char): Boolean {
            return oldItem == newItem
        }

    }

}