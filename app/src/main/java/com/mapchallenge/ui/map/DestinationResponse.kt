package com.mapchallenge.ui.map


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable

@Parcelize
data class Trip(
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("time")
    val time: String?
) : Parcelable
