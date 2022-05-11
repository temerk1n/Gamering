package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Streams(
    val english: String,
    val official: String,
    val russian: String
) : Parcelable