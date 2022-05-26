package com.example.sch

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private var dateAndTime = Calendar.getInstance()
    private lateinit var datePickerDialog: DatePickerDialog
    private var dateArray : MutableList<String> = mutableListOf()
    private lateinit var bundle : Bundle
    //private lateinit var mainFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment

        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)


        return true
    }
    // Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.filters -> {
                dateArray = setDate()
                bundle = bundleOf("month" to dateArray[0], "dayOfMonth" to dateArray[1])

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDate() : MutableList<String> {
        val dateArr : MutableList<String> = mutableListOf()
        Log.d("Filters", "2")
        this.let {
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
//        binding.datePickerActions.text = DateUtils.formatDateTime(
//            context,
//            dateAndTime.timeInMillis,
//            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
//
//        )
    }

    // установка обработчика выбора даты
    private var dateHandler =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
            setInitialDateTime()
        }
}