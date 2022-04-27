package com.applogist.mapchallenge.ui.trips

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.base.BaseFragment
import com.applogist.mapchallenge.databinding.FragmentTripsBinding
import com.applogist.mapchallenge.ui.map.Trip

class TripsFragment : BaseFragment<FragmentTripsBinding>() {
    val args: TripsFragmentArgs by navArgs()
    override fun getLayoutId(): Int = R.layout.fragment_trips
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TripsAdapter { trip ->
            tripItemClickListener(trip)
        }
        binding.tripsRecyclerView.adapter = adapter
        adapter.updateItems(args.trips)
    }

    private fun tripItemClickListener(trip: Trip) {

    }
}