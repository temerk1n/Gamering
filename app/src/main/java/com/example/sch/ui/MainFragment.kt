package com.example.sch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
//        val model : FiltersFragmentViewModel =
//            ViewModelProviders.of(requireActivity()).get(FiltersFragmentViewModel::class.java)
//        model.getSelected().observe(viewLifecycleOwner, Observer<List<String>> {
//            model.getSelected().value?.let { it1 -> viewModel.getParams(it1) }
//        })
        viewModel.matchData
            .subscribeOn(Schedulers.io())
            .doOnNext {
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


}