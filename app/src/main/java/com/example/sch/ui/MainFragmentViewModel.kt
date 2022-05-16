package com.example.sch.ui

import androidx.lifecycle.ViewModel
import com.example.sch.data.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {
    val matchData = repository.getSchedule()
}