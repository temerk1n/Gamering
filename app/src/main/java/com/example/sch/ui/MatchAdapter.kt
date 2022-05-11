package com.example.sch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sch.data.MatchItem
import com.example.sch.databinding.MatchItemBinding

class MatchAdapter : ListAdapter<MatchItem, MatchAdapter.MatchViewHolder>(DiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding =
            MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MatchViewHolder(private val binding: MatchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(matchItem: MatchItem) {
            binding.apply {
                beginAt.text = matchItem.begin_at

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MatchItem>() {

        override fun areItemsTheSame(
            oldItem: MatchItem,
            newItem: MatchItem
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MatchItem,
            newItem: MatchItem
        ) =
            oldItem == newItem
    }
}