package com.example.sch.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val begin_at: String,
    val complete: Boolean,
    val detailed_stats: Boolean,
    val end_at: String,
    val finished: Boolean,
    val forfeit: Boolean,
    val id: Int,
    val length: String,
    val match_id: Int,
    val position: Int,
    val status: String,
    val video_url: String,
    val winner: Winner,
    val winner_type: String
) : Parcelable