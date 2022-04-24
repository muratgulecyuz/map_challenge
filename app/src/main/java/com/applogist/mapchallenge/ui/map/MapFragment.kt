package com.applogist.mapchallenge.ui.map

import android.os.Bundle
import android.view.View
import com.applogist.mapchallenge.R
import com.applogist.mapchallenge.base.BaseFragment
import com.applogist.mapchallenge.databinding.FragmentMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_map
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.getDestinations()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}