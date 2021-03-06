package com.mapchallenge.ui.map

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mapchallenge.R
import com.mapchallenge.base.BaseFragment
import com.mapchallenge.databinding.FragmentMapBinding
import com.mapchallenge.utils.BOOKED_DESTINATION
import com.mapchallenge.utils.bitmapDescriptorFromVector
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
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
        getCurrentLocation()
        observeBookedDestination()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        initMap(googleMap)
        markerClickListener()
        viewModel.getDestinations()
    }

    private fun observeBookedDestination() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<DestinationResponse>(
            BOOKED_DESTINATION
        )
            ?.observe(
                viewLifecycleOwner
            ) { result ->
                result?.id?.let {
                    viewModel.getSelectedMarker(it)
                        ?.setIcon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_checked))

                }
            }
    }

    private fun mapClickListener() {
        mMap.setOnMapClickListener {
            binding.listTripsButton.visibility = View.GONE
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    moveCamera(it)
                }

            }
    }

    private fun moveCamera(location: Location) {
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    location.latitude,
                    location.longitude
                ), 10f
            )
        )
    }

    private fun initMapFragment() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initMap(googleMap: GoogleMap) {
        mMap = googleMap
        mapClickListener()
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
            viewModel.getSelectedDestinaton()?.let { destination ->
                findNavController().navigate(
                    MapFragmentDirections.actionMapFragmentToTripsFragment(
                        destination
                    )
                )
            }
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
                    viewModel.addMarker(mMap)

                }
                STATUS_ERROR -> {

                }
            }
        }
    }
}