package com.applogist.mapchallenge.ui.trips

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.databinding.ItemTripLayoutBinding
import com.applogist.mapchallenge.ui.map.Trip

class TripsAdapter(private val clickListener: (trip: Trip) -> Unit) : RecyclerView.Adapter<TripsViewHolder>() {

    private var tripList: List<Trip> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripsViewHolder {
        val binding: ItemTripLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_trip_layout,
            parent,
            false
        )
        return TripsViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = tripList.size

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {
        holder.bind(tripList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Trip>?) {
        tripList = items ?: emptyList()
        notifyDataSetChanged()
    }
}

