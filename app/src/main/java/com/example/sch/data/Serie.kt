package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(
    val begin_at: String,
    val description: String,
    val end_at: String,
    val full_name: String,
    val id: Int,
    val league_id: Int,
    val modified_at: String,
    val name: String,
    val season: String,
    val slug: String,
    val tier: String,
    val winner_id: String,
    val winner_type: String,
    val year: Int
) : Parcelable