package com.example.sch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sch.R
import com.example.sch.api.ScheduleApi
import com.example.sch.data.MatchDataState
import com.example.sch.data.MatchItem
import com.example.sch.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var dataState: MatchDataState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        dataState = MatchDataState.LOADING

        val matchAdapter = MatchAdapter()

        binding.apply {
            matchContainer.apply {
                adapter = matchAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        updateUI()

        //val matchData =


    }



    private fun updateUI() {
        binding.apply {
            when(dataState) {
                MatchDataState.LOADING -> {
                    progressBar.isVisible = true
                    textViewEmpty.isVisible = false
                    matchContainer.isVisible = false
                }
                MatchDataState.EMPTYCACHE -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = true
                    matchContainer.isVisible = false
                }
                MatchDataState.SUCCESS -> {
                    progressBar.isVisible = false
                    textViewEmpty.isVisible = false
                    matchContainer.isVisible = true
                }
            }
        }
    }

    private fun tryToFetchSchedule() {
        // for DB
        val itemsToInsert = arrayListOf<MatchItem>()

        val scheduleApi : ScheduleApi

        // RXJAVA
        //scheduleApi.getMatch().
    }
}