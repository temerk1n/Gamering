package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpponentX(
    val acronym: Boolean,
    val id: Int,
    val image_url: String,
    val location: String,
    val modified_at: String,
    val name: String,
    val slug: String
) : Parcelable