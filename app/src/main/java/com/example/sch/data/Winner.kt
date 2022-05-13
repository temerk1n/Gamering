package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Winner(
    val id: String,
    val type: String
) : Parcelable