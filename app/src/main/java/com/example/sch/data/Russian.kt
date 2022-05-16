package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Russian(
    val embed_url: String,
    val raw_url: String
) : Parcelable