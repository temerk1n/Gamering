package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val id: Int,
    val image_url: String,
    val modified_at: String,
    val name: String,
    val slug: String,
    val url: String
) : Parcelable