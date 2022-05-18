package com.example.sch.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sch.databinding.FragmentFiltersBinding
import java.util.*


// TODO поменять название фрагмента при переходе на FilterFragment
class FiltersFragment : Fragment() {

    private lateinit var binding: FragmentFiltersBinding

    private lateinit var currentDateTime : String
    private var dateAndTime = Calendar.getInstance()
    private lateinit var datePickerDialog: DatePickerDialog

    private var dateArray : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentFiltersBinding.inflate(inflater, container, false)

        val model : FiltersFragmentViewModel = ViewModelProviders.of(requireActivity())[FiltersFragmentViewModel::class.java]

        setInitialDateTime()
        // Actions with calendar
        binding.datePickerActions.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                dateArray = setDate()
                model.select(dateArray)
            }
        })


        return binding.root
    }

    fun setDate(): MutableList<String> {
        context?.let {
            DatePickerDialog(
                it, dateHandler,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }
        var dateArr : MutableList<String> = mutableListOf()
        dateArr.add(dateAndTime.get(Calendar.MONTH).toString())
        dateArr.add(dateAndTime.get(Calendar.DAY_OF_MONTH).toString())
        return dateArr
    }

    private fun setInitialDateTime() {
        binding.datePickerActions.text = DateUtils.formatDateTime(
            context,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
    }

    // установка обработчика выбора даты
    var dateHandler =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
            setInitialDateTime()
        }

}