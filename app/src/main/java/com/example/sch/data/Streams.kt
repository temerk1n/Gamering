package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Streams(
    val english: English,
    val official: Official,
    val russian: Russian
) : Parcelable