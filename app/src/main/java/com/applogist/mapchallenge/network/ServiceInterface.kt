package com.applogist.mapchallenge.network

import com.applogist.mapchallenge.ui.map.data.response.DestinationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceInterface {
    @GET("case-study/6/stations")
    suspend fun fetchDestinations(): List<DestinationResponse>
}