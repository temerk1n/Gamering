package com.example.sch.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sch.R
import com.example.sch.data.MatchItem
import com.example.sch.data.MatchItemSimplified
import com.example.sch.databinding.MatchItemBinding
import com.squareup.picasso.Picasso

class MatchAdapter : ListAdapter<MatchItemSimplified, MatchAdapter.MatchViewHolder>(DiffCallback()){
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

        fun bind(matchItem: MatchItemSimplified) {
            binding.apply {
                //imageView = matchItem
                tournamentName.text = matchItem.tournament_name
                firstOpponentName.text = matchItem.firstOpponentName
                secondOpponentName.text = matchItem.secondOpponentName
                if (matchItem.firstOpponentImageURL != null) {
                    Picasso.get().load(matchItem.firstOpponentImageURL).into(firstOpponentImage)
                } else firstOpponentImage.setImageResource(R.drawable.ic_stat_name)
                if (matchItem.secondOpponentImageURL != null) {
                    Picasso.get().load(matchItem.secondOpponentImageURL).into(secondOpponentImage)
                } else secondOpponentImage.setImageResource(R.drawable.ic_stat_name)

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MatchItemSimplified>() {

        override fun areItemsTheSame(
            oldItem: MatchItemSimplified,
            newItem: MatchItemSimplified
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MatchItemSimplified,
            newItem: MatchItemSimplified
        ) =
            oldItem == newItem
    }
}