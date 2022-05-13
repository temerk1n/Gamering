package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stream(
    val embed_url : String,
    val language : String,
    val main : Boolean,
    val official: Boolean,
    val raw_url : String
) : Parcelable
