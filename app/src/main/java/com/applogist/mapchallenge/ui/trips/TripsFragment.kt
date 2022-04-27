package com.applogist.mapchallenge.ui.trips

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.base.BaseFragment
import com.applogist.mapchallenge.databinding.FragmentTripsBinding
import com.applogist.mapchallenge.ui.map.Trip
import com.applogist.mapchallenge.utils.showDialog
import com.murgupluoglu.request.STATUS_ERROR
import com.murgupluoglu.request.STATUS_LOADING
import com.murgupluoglu.request.STATUS_SUCCESS
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripsFragment : BaseFragment<FragmentTripsBinding>() {
    private val args: TripsFragmentArgs by navArgs()
    private val viewModel: TripsViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_trips

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TripsAdapter { trip ->
            tripItemClickListener(trip)
        }
        binding.tripsRecyclerView.adapter = adapter
        adapter.updateItems(args.destination.trips)
        observeBooking()
    }

    private fun tripItemClickListener(trip: Trip) {
        args.destination.id?.let { destinationId ->
            trip.id?.let { tripId ->
                viewModel.fetchBooking(destinationId, tripId)
            }
        }
    }

    private fun observeBooking() {
        viewModel.observeBookingResponse().observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS_LOADING -> {
                }
                STATUS_SUCCESS -> {
                    requireContext().showDialog()

                }
                STATUS_ERROR -> {
                    requireContext().showDialog()
                }
            }
        }
    }
}