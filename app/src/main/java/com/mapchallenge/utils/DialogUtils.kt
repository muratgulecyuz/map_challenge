package com.mapchallenge.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.mapchallenge.R
import com.google.android.material.button.MaterialButton

fun Context.showDialog() {
    MaterialDialog(this).show {
        cancelable(true)
        cancelOnTouchOutside(true)
        setContentView(R.layout.view_custom_dialog)
        findViewById<MaterialButton>(R.id.selectTripButton).setOnClickListener { dismiss() }
    }
}