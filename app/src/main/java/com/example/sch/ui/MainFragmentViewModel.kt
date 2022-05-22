package com.example.sch.ui

import android.content.SharedPreferences
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.sch.data.MatchItemSimplified
import com.example.sch.data.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import java.util.*
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