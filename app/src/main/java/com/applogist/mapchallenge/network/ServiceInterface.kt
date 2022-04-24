package com.applogist.mapchallenge.network

import com.applogist.mapchallenge.ui.map.DestinationResponse
import retrofit2.http.GET

interface ServiceInterface {
    @GET("case-study/6/stations")
    suspend fun fetchDestinations(): List<DestinationResponse>
}