package com.applogist.mapchallenge.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applogist.mapchallenge.network.ServiceInterface
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
}