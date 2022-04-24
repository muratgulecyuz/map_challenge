package com.applogist.mapchallenge.ui.map


import com.google.gson.annotations.SerializedName

data class DestinationResponse(
    @SerializedName("center_coordinates")
    val centerCoordinates: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("trips")
    val trips: List<Trip>?,
    @SerializedName("trips_count")
    val tripsCount: Int?,
    var isSelected: Boolean = false
)

data class Trip(
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("time")
    val time: String?
)