package com.applogist.mapchallenge.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applogist.mapchallenge.network.ServiceInterface
import com.murgupluoglu.request.RESPONSE
import com.murgupluoglu.request.request

class MapViewModel(private val serviceInterface: ServiceInterface) : ViewModel() {
    val destinationsResponse: MutableLiveData<RESPONSE<List<DestinationResponse>>> =
        MutableLiveData()

    var markerList = listOf<DestinationResponse>()

    fun observeDestinationsResponse() = destinationsResponse


    fun getDestinations() {
        destinationsResponse.request({ serviceInterface.fetchDestinations() })
    }

    fun selectMarker(tag: Int) {
        clearSelections()
        markerList.find { it.id == tag }?.isSelected = true
    }

    private fun clearSelections() {
        markerList.forEach { it.isSelected = false }
    }

    fun getSelectedMarker(): DestinationResponse? {
        return markerList.find { it.isSelected }
    }
}