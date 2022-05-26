package com.example.sch.ui

import androidx.lifecycle.ViewModel
import com.example.sch.data.MatchItemSimplified
import com.example.sch.data.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import javax.inject.Inject
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {
    val date : MutableList<String> = mutableListOf()

    var matchData = getMatches()

    private fun getMatches(): Observable<List<MatchItemSimplified>> {
        return repository.getSchedule(date)
    }

    fun updateMatches() {
        this.matchData = getMatches()
    }
}