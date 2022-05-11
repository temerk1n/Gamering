package com.example.sch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sch.data.MatchItem

class MatchAdapter : ListAdapter<MatchItem, MatchAdapter.MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding =
            WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MatchViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherItem: MatchItem) {
            binding.apply {
                //dtField.text = formatDate(weatherItem.dt_txt)

            }
        }
    }
}