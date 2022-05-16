package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opponent(
    val opponent: OpponentX,
    val type: String
) : Parcelable