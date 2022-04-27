package com.applogist.mapchallenge.ui.trips

import androidx.recyclerview.widget.RecyclerView
import com.applogist.mapchallenge.databinding.ItemTripLayoutBinding
import com.applogist.mapchallenge.ui.map.Trip

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