package com.example.busbooking

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.example.busbooking.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var bindig: ActivityMainBinding
    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindig = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindig.root)

        bindig.cvDate.setOnClickListener {

            showDatePickerDialog()
            bindig.tvDate.text = date
        }
        bindig.cvFrom.setOnClickListener {

            startActivity(Intent(this, SelectRegionActivity::class.java))

        }

        bindig.cvSearch.setOnClickListener {

            startActivity(Intent(this, PlaceActivity::class.java))

        }

    }


    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                date = formattedDate


            },
            currentYear,
            currentMonth,
            currentDay
        )


        datePickerDialog.show()
    }
}
