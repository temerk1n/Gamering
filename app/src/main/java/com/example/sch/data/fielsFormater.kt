package com.example.sch.data

import java.time.format.DateTimeFormatter.ISO_INSTANT

fun formatDate(Date: String) : MutableList<String> {
//    val monthName = when (Date.subSequence(5, 7)) {
//        "01" -> "January"
//        "02" -> "February"
//        "03" -> "March"
//        "04" -> "April"
//        "05" -> "May"
//        "06" -> "June"
//        "07" -> "July"
//        "08" -> "August"
//        "09" -> "September"
//        "10" -> "October"
//        "11" -> "November"
//        "12" -> "December"
//        else -> "Not a month or error"
//    }

    var date = Date.subSequence(8, 10)
    var hours = Date.subSequence(11, 13)
    val minutes = Date.subSequence(14, 16)
    //var hoursInt = hours.toString().toInt()

//    // +3 hours
//    hoursInt += 3

//    if (hoursInt >= 24) {
//        hoursInt -= 24
//        val dateInt = date.toString().toInt() + 1
//        date = dateInt.toString()
//        hours = "0$hoursInt"
//    } else {
//        hours = hoursInt.toString()
//    }

    val dateArr : MutableList<String> = mutableListOf()
    dateArr.add(Date.subSequence(5, 7).toString()) // month
    dateArr.add(date.toString()) // month day
    dateArr.add(hours.toString()) // hour
    dateArr.add(minutes.toString()) // minutes
    return dateArr
}

fun formatDateToShow(dateArr : MutableList<String>) : String {
    val monthName = when (dateArr[0]) {
        "01" -> "January"
        "02" -> "February"
        "03" -> "March"
        "04" -> "April"
        "05" -> "May"
        "06" -> "June"
        "07" -> "July"
        "08" -> "August"
        "09" -> "September"
        "10" -> "October"
        "11" -> "November"
        "12" -> "December"
        else -> "Not a month or error"
    }

    var date = dateArr[1]
    var hours = dateArr[2]
    val minutes = dateArr[3]
    var hoursInt = hours.toInt()

    // +3 hours
    hoursInt += 3


    if (hoursInt >= 24) {
        hoursInt -= 24
        val dateInt = date.toInt() + 1
        date = dateInt.toString()
        hours = "0$hoursInt"
    } else {
        hours = hoursInt.toString()
    }
    return "$date $monthName $hours:$minutes"
}