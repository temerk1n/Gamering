package com.example.sch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class MatchItemSimplified (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val tournament_name : String,
    val original_scheduled_at : String,
    val firstOpponentName : String,
    val secondOpponentName : String,
    val firstOpponentImageURL: String?,
    val secondOpponentImageURL: String?,
)