package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Live(
    val opens_at: String,
    val supported: Boolean,
    val url: String
) : Parcelable