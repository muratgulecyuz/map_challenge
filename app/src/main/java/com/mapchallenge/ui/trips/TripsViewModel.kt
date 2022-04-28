package com.mapchallenge.ui.trips

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mapchallenge.network.ServiceInterface
import com.murgupluoglu.request.RESPONSE
import com.murgupluoglu.request.request

class TripsViewModel(private val serviceInterface: ServiceInterface) : ViewModel() {
    private val bookingResponse: MutableLiveData<RESPONSE<Any>> = MutableLiveData()
    fun observeBookingResponse() = bookingResponse


    fun fetchBooking(stationId: Int, tripId: Int) {
        bookingResponse.request({ serviceInterface.fetchBooking(stationId, tripId) })
    }
}