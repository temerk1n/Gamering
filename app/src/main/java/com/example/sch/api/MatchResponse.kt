package com.example.sch.api

import android.os.Parcelable
import com.example.sch.data.MatchItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResponse(
    val list: List<MatchItem>
) : Parcelable