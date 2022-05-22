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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    //private val dateArr : MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val pref: SharedPreferences? = context?.getSharedPreferences("key_value", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = pref?.edit()
        binding.datePickerActions.setOnClickListener {
            dateArray = setDate()

            Log.d("Filters", dateArray[0])
            Log.d("Filters", dateArray[1])
            editor?.putString("MONTH_KEY", dateArray[0])
            editor?.putString("MONTH_DAY_KEY", dateArray[1])
            editor?.apply()


        }


        return binding.root
    }



    private fun setDate() : MutableList<String> {
        val dateArr : MutableList<String> = mutableListOf()
        context?.let {
            DatePickerDialog(
                it, dateHandler,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            )
                .show()
            dateArr.add((dateAndTime.get(Calendar.MONTH)+1).toString())
            dateArr.add((dateAndTime.get(Calendar.DAY_OF_MONTH)).toString())

        }
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