package com.mapchallenge.ui.trips

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mapchallenge.R
import com.mapchallenge.base.BaseFragment
import com.mapchallenge.databinding.FragmentTripsBinding
import com.mapchallenge.ui.map.Trip
import com.mapchallenge.utils.BOOKED_DESTINATION
import com.mapchallenge.utils.showDialog
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
        initAdapter()
        observeBooking()
    }

    private fun initAdapter() {
        val adapter = TripsAdapter { trip ->
            tripItemClickListener(trip)
        }
        binding.tripsRecyclerView.adapter = adapter
        adapter.updateItems(args.destination.trips)
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
                    findNavController().previousBackStackEntry?.savedStateHandle?.set(
                        BOOKED_DESTINATION,
                        args.destination
                    )
                    findNavController().popBackStack()
                }
                STATUS_ERROR -> {
                    requireContext().showDialog()
                }
            }
        }
    }
}