package com.mapchallenge.ui.trips

import androidx.recyclerview.widget.RecyclerView
import com.mapchallenge.databinding.ItemTripLayoutBinding
import com.mapchallenge.ui.map.Trip

class TripsViewHolder(
    private val binding: ItemTripLayoutBinding,
    private val clickListener: (trip: Trip) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: Trip) {
        binding.tripNameTextView.text = itemViewModel.busName
        binding.tripTimeTextView.text = itemViewModel.time
        binding.bookButton.setOnClickListener { clickListener.invoke(itemViewModel) }
    }
}