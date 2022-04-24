package com.applogist.mapchallenge.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applogist.mapchallenge.network.ServiceInterface
import com.applogist.mapchallenge.ui.map.data.response.DestinationResponse
import com.murgupluoglu.request.RESPONSE
import com.murgupluoglu.request.request

class MapViewModel(val serviceInterface: ServiceInterface) : ViewModel() {
    val destinationsResponse: MutableLiveData<RESPONSE<List<DestinationResponse>>> =
        MutableLiveData()

    fun getDestinations() {
        destinationsResponse.request({ serviceInterface.fetchDestinations() })
    }
}