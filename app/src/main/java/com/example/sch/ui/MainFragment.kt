package com.example.sch.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sch.R
import com.example.sch.data.MatchDataState
import com.example.sch.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainFragmentViewModel by viewModels()
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


        val sharedPreferences = context?.getSharedPreferences("key_value", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        val dateArray : MutableList<String> = mutableListOf()


        viewModel.matchData
            .subscribeOn(Schedulers.io())
            .doOnNext {
                val month = sharedPreferences?.getString("MONTH_KEY", null)
                val monthDay = sharedPreferences?.getString("MONTH_DAY_KEY", null)
                if (month != null) {
                    dateArray.add(month)
                    viewModel.date.add(month)
                }
                if (monthDay != null) {
                    dateArray.add(monthDay)
                    viewModel.date.add(monthDay)
                }
                Log.d("Main", dateArray[0])
                Log.d("Main", dateArray[1])
                matchAdapter.submitList(it)
            }
            .delay(250, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
                dataState = if (matchAdapter.itemCount == 0)
                    MatchDataState.EMPTYCACHE
                else
                    MatchDataState.SUCCESS
                updateUI()
            }
            .subscribe()

        binding.root.setOnRefreshListener {
            updateUI()
            binding.root.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

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

    private fun init() {
        //sharedPreferences = activity?.getSharedPreferences("date", Context.MODE_PRIVATE)!!


    }


}