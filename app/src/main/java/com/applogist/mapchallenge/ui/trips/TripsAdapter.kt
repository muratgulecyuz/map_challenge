package com.applogist.mapchallenge.ui.trips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.databinding.ItemTripLayoutBinding
import com.applogist.mapchallenge.ui.map.Trip

class TripsAdapter(val clickListener: (trip: Trip) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    var itemViewModels: List<Trip> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTripLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_trip_layout,
            parent,
            false
        )
        return ViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = itemViewModels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemViewModels[position])
    }

    fun updateItems(items: List<Trip>?) {
        itemViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }
}

class ViewHolder(
    private val binding: ItemTripLayoutBinding,
    val clickListener: (trip: Trip) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemViewModel: Trip) {
        binding.tripNameTextView.text = itemViewModel.busName
        binding.tripTimeTextView.text = itemViewModel.time
        binding.bookButton.setOnClickListener { clickListener.invoke(itemViewModel) }
    }
}