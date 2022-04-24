package com.applogist.mapchallenge.utils

fun String.splitCoordinates(): Pair<Double, Double> {
    val coordinates = split(",").map { it.toDouble() }
    return Pair(coordinates[0], coordinates[1])
}