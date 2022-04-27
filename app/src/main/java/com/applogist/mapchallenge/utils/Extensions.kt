package com.applogist.mapchallenge.utils

fun String.splitCoordinates(): Pair<Double, Double>? {
    val coordinates = split(",").map { it.toDouble() }
    return if (coordinates.size == 2) {
        Pair(coordinates[0], coordinates[1])
    } else {
        null
    }
}