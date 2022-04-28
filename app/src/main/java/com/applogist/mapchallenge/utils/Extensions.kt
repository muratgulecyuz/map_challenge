package com.applogist.mapchallenge.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


fun String.splitCoordinates(): Pair<Double, Double>? {
    val coordinates = split(",").map { it.toDouble() }
    return if (coordinates.size == 2) {
        Pair(coordinates[0], coordinates[1])
    } else {
        null
    }
}

fun Context.bitmapDescriptorFromVector(
    @DrawableRes vectorDrawableResourceId: Int
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorDrawableResourceId)
    vectorDrawable!!.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}