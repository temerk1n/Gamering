package com.example.sch.data

import androidx.room.TypeConverter

class DateConverter {

    @TypeConverter
    fun fromDate(date : String): MutableList<String> {
        return formatDate(date)
    }

    @TypeConverter
    fun toDate(dateArr : MutableList<String>) : String {
        return "2022-${dateArr[0]}-${dateArr[1]}T${dateArr[2]}:${dateArr[3]}:00Z"
    }
}