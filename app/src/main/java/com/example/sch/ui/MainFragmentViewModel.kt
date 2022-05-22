package com.example.sch.ui

import android.content.SharedPreferences
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
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
    val date : MutableList<String> = mutableListOf()
    val matchData = repository.getSchedule(date)

}