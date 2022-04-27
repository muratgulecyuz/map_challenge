package com.applogist.mapchallenge.ui.map

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.base.BaseFragment
import com.applogist.mapchallenge.databinding.FragmentMapBinding
import com.applogist.mapchallenge.utils.splitCoordinates
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.murgupluoglu.request.STATUS_ERROR
import com.murgupluoglu.request.STATUS_LOADING
import com.murgupluoglu.request.STATUS_SUCCESS
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_map
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMapFragment()
        observeDestinations()
        tripsButtonClickListener()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        initMap(googleMap)
        markerClickListener()
        viewModel.getDestinations()
    }

    private fun initMapFragment() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initMap(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun markerClickListener() {
        mMap.setOnMarkerClickListener { marker ->
            binding.listTripsButton.visibility = View.VISIBLE
            viewModel.selectMarker(marker.tag as Int)
            false
        }
    }

    private fun tripsButtonClickListener() {
        binding.listTripsButton.setOnClickListener {
            viewModel.getSelectedMarker()?.let { destination ->
                findNavController().navigate(
                    MapFragmentDirections.actionMapFragmentToTripsFragment(
                        destination
                    )
                )
            }


        }
    }

    private fun addMarker(destination: DestinationResponse) {
        val destinationCoordinates =
            destination.centerCoordinates?.splitCoordinates()

        destinationCoordinates?.let { coordinateCouple ->
            val markerOptions = MarkerOptions().position(
                LatLng(
                    coordinateCouple.first,
                    coordinateCouple.second
                )
            ).title(destination.trips?.size.toString() + "trips")


            mMap.addMarker(
                markerOptions
            )?.tag = destination.id
        }

    }

    private fun observeDestinations() {
        viewModel.observeDestinationsResponse().observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS_LOADING -> {
                }
                STATUS_SUCCESS -> {
                    val destinations = it.responseObject
                    viewModel.destinationList = destinations ?: listOf()
                    destinations?.forEach { destination ->
                        addMarker(destination)
                    }
                }
                STATUS_ERROR -> {

                }
            }
        }
    }
}