package com.mapchallenge.network

import com.mapchallenge.ui.map.DestinationResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceInterface {
    @GET("case-study/6/stations")
    suspend fun fetchDestinations(): List<DestinationResponse>

    @POST("case-study/6/stations/{stationId}/trips/{tripId}")
    suspend fun fetchBooking(
        @Path("stationId") stationId: Int,
        @Path("tripId") tripId: Int
    ): Any
}