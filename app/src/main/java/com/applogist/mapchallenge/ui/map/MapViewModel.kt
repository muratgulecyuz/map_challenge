package com.applogist.mapchallenge.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applogist.mapchallenge.network.ServiceInterface
import com.applogist.mapchallenge.utils.splitCoordinates
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.murgupluoglu.request.RESPONSE
import com.murgupluoglu.request.request

class MapViewModel(private val serviceInterface: ServiceInterface) : ViewModel() {
    private val destinationsResponse: MutableLiveData<RESPONSE<List<DestinationResponse>>> =
        MutableLiveData()

    var destinationList = listOf<DestinationResponse>()

    fun observeDestinationsResponse() = destinationsResponse


    fun getDestinations() {
        destinationsResponse.request({ serviceInterface.fetchDestinations() })
    }

    fun selectMarker(tag: Int) {
        clearSelections()
        destinationList.find { it.id == tag }?.isSelected = true
    }

    private fun clearSelections() {
        destinationList.forEach { it.isSelected = false }
    }

    fun getSelectedMarker(): DestinationResponse? {
        return destinationList.find { it.isSelected }
    }

    fun addMarker(map: GoogleMap) {
        destinationList.forEach { destination ->

            val destinationCoordinates =
                destination.centerCoordinates?.splitCoordinates()

            destinationCoordinates?.let { coordinateCouple ->
                val markerOptions = MarkerOptions().position(
                    LatLng(
                        coordinateCouple.first,
                        coordinateCouple.second
                    )
                ).title(destination.trips?.size.toString() + "trips")


                map.addMarker(
                    markerOptions
                )?.tag = destination.id
            }

        }
    }
}