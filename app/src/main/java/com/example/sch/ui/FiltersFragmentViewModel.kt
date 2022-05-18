package com.example.sch.ui

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


class FiltersFragmentViewModel  : ViewModel() {
    
    private var currentDate: Date = Calendar.getInstance().time
    val formatter = SimpleDateFormat("MM/dd", Locale.getDefault())
    private var currentDateInString = formatter.format(currentDate)
    private var month = currentDateInString.subSequence(0, 2).toString()
    private var monthDay = currentDateInString.subSequence(3, 5).toString()
    private var list : MutableList<String> = mutableListOf(month, monthDay)

    private lateinit var date : MutableLiveData<List<String>>


    fun select(item : List<String>) {
        date.value = item
    }

    fun getSelected(): LiveData<List<String>> {
        return date
    }

}