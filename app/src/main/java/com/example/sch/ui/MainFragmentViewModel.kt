package com.example.sch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.sch.data.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

//    private lateinit var month : String
//    private lateinit var monthDay : String
//    fun getParams(date : List<String>) {
//        this.month = date[0]
//        this.monthDay = date[1]
//    }

    val matchData = repository.getSchedule()


}