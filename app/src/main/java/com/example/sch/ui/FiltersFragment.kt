package com.example.sch.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sch.R
import com.example.sch.databinding.FragmentFiltersBinding
import java.util.*


// TODO поменять название фрагмента при переходе на FilterFragment
class FiltersFragment : Fragment() {

    private lateinit var binding: FragmentFiltersBinding
    private val viewModel : MainFragmentViewModel by activityViewModels()

    private lateinit var currentDateTime : String
    private var dateAndTime = Calendar.getInstance()
    private lateinit var datePickerDialog: DatePickerDialog

    private var dateArray : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        //val model : FiltersFragmentViewModel = ViewModelProviders.of(requireActivity())[FiltersFragmentViewModel::class.java]

        setInitialDateTime()
        // Actions with calendar


        binding.datePickerActions.setOnClickListener {
            Log.d("Filters", "1")
            dateArray = setDate()
            Log.d("Filters", "4")
//            if (dateArray.isNotEmpty()) {
//                Log.d("Filters", dateArray[0])
//                Log.d("Filters", dateArray[1])
//                editor?.putString("MONTH_KEY", dateArray[0])
//                editor?.putString("MONTH_DAY_KEY", dateArray[1])
//                editor?.apply()
//            }
        }

        binding.btn.setOnClickListener {

            if (dateArray.isNotEmpty()) {
                findNavController().navigate(
                    R.id.action_filtersFragment_to_mainFragment,
                    bundleOf("month" to dateArray[0], "dayOfMonth" to dateArray[1]))
            } else {
                findNavController().navigate(R.id.action_filtersFragment_to_mainFragment)
            }

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun setDate() : MutableList<String> {
        val dateArr : MutableList<String> = mutableListOf()
        Log.d("Filters", "2")
        context?.let {
            datePickerDialog = DatePickerDialog(
                it, dateHandler,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.setOnDismissListener {
                dateArr.add((dateAndTime.get(Calendar.MONTH)+1).toString())
                dateArr.add((dateAndTime.get(Calendar.DAY_OF_MONTH)).toString())
            }
            datePickerDialog.show()
        }
        Log.d("Filters", "3")
        return dateArr
    }

    private fun setInitialDateTime() {
        binding.datePickerActions.text = DateUtils.formatDateTime(
            context,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR

        )
    }

    // установка обработчика выбора даты
    private var dateHandler =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
            setInitialDateTime()
        }

}