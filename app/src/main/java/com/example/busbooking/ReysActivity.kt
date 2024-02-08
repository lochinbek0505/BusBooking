package com.example.busbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.busbooking.adapters.TicketsAdapter
import com.example.busbooking.databinding.ActivityReysBinding
import com.example.busbooking.model.tickets
import kotlin.random.Random

class ReysActivity : AppCompatActivity() {

    lateinit var binding:ActivityReysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityReysBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val array = ArrayList<tickets>()

        val arrayList = intent.getStringArrayListExtra("arrayList")

        binding.ivArBack.setOnClickListener {

            finish()

        }

        val dataList = listOf(
            "Andijon viloyati",
            "Buxoro viloyati",
            "Farg'ona viloyati",
            "Jizzax viloyati",
            "Qaraqalpog'iston respublikasi",
            "Qashqadaryo viloyati",
            "Xorazm viloyati",
            "Namangan viloyati",
            "Navoiy viloyati",
            "Samarqand viloyati",
            "Sirdaryo viloyati",
            "Surxondaryo viloyati",
            "Tashkent viloyati"
        )

        val generatedObjects = generateRandomStrings(dataList, arrayList!!.get(0), arrayList.get(2))

        val minTime = 7 // minimum time in minutes
        val maxTime = 17 // maximum time in minutes
        val minPrice = 45 // minimum price
        val maxPrice = 90 // maximum price

        array.add(
            tickets(
                arrayList.get(1),
                arrayList.get(3),
                arrayList.get(4),
                generateRandomValue(minPrice, maxPrice) *1000 ,
                "${generateRandomValue(minTime, maxTime)}:00"
            )
        )
        array.add(
            tickets(
                arrayList.get(0),
                arrayList.get(2),
                arrayList.get(4),
                generateRandomValue(minPrice, maxPrice) *1000,
                "${generateRandomValue(minTime, maxTime)}:00"
            )
        )
        array.add(
            tickets(
                generatedObjects.get(0),
                generatedObjects.get(1),
                arrayList.get(4),
                generateRandomValue(minPrice, maxPrice) *1000,
                "${generateRandomValue(minTime, maxTime)}:00"
            )
        )

        val adapter=TicketsAdapter(array,object :TicketsAdapter.ItemSetOnClickListener{
            override fun onClick(data: tickets) {
                val intent=Intent(this@ReysActivity,PlaceActivity::class.java)

                intent.putExtra("myData", data)
                startActivity(intent)

            }


        })

        binding.arRecycler.adapter=adapter

    }

    private fun generateRandomValue(min: Int, max: Int): Int {
        return Random.nextInt(
            min,
            max + 1
        )
    }

    fun generateRandomStrings(list: List<String>, input1: String, input2: String): ArrayList<String> {
        val result = ArrayList<String>()

        // Find the indices of the input strings in the list
        val index1 = list.indexOf(input1)
        val index2 = list.indexOf(input2)

        // Ensure that at least one of the input strings is participating
        if (index1 == -1 && index2 == -1) {
            return result
        }

        // Generate two random indices between the indices of the input strings
        var randomIndex1 = index1
        var randomIndex2 = index2
        while (randomIndex1 == index1 || randomIndex2 == index2 || randomIndex1 == randomIndex2) {
            randomIndex1 = (0 until list.size).random()
            randomIndex2 = (0 until list.size).random()
        }

        // Add the randomly selected strings to the result
        result.add(list[randomIndex1])
        result.add(list[randomIndex2])

        return result
    }

}