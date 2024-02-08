package com.example.busbooking

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.busbooking.adapters.SelectRegionAdapter
import com.example.busbooking.databinding.ActivitySelectRegionBinding

class SelectRegionActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectRegionBinding
    lateinit var adapter: SelectRegionAdapter
    private val PREF_NAME1 = "my_preferences1"
    private val PREF_NAME2 = "my_preferences2"

    lateinit var list: MutableList<String>
    lateinit var repons2: ArrayList<String>
    lateinit var repons: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectRegionBinding.inflate(layoutInflater)

        setContentView(binding.root)

        repons = ArrayList()
        repons2 = ArrayList()

        val vil = intent.getStringExtra("Vil2")
        val reg = intent.getStringExtra("reg2")


        Log.e("test2", "$vil $reg")
        if (!vil.isNullOrEmpty() && !reg.isNullOrEmpty()) {

            repons2.add(vil)
            repons2.add(reg)


        }


        list = mutableListOf(
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

        adapter = SelectRegionAdapter(list,
            object : SelectRegionAdapter.ItemSetOnClickListener {
                override fun onClick(data: String, position: Int) {

                    binding.btnBack.visibility = View.VISIBLE
                    repons.add(data)
                    Log.e("Response", repons.toString())
                    if (repons.size == 2) {

                        if (repons != repons2) {

                            if (repons2.isNullOrEmpty()) {

                                saveStringsToPreferences("Vil","reg", repons.get(0),repons.get(1),PREF_NAME1)
                                val intent =
                                    Intent(this@SelectRegionActivity, HomeActivity::class.java)

                                startActivity(intent)
                                finish()
                            }
                            else{
                                saveStringsToPreferences("Vil3","reg3", repons.get(0),repons.get(1),PREF_NAME2)

                                val intent =
                                    Intent(this@SelectRegionActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()

                            }
                        } else {

                            giveRegion(0, false)
                            binding.btnBack.visibility = View.INVISIBLE
                            Toast.makeText(
                                this@SelectRegionActivity,
                                "Joylashuv manzilini tanlashda xatolik",
                                Toast.LENGTH_LONG
                            ).show()

                        }

                    } else {
                        giveRegion(position, true)

                    }
                }


            })
        binding.asrRecycler.adapter = adapter

        binding.btnClear.setOnClickListener {

            finish()

        }

        binding.btnBack.setOnClickListener {
            giveRegion(0, false)
            binding.btnBack.visibility = View.INVISIBLE
        }

    }

    fun giveRegion(index: Int, ch: Boolean) {


        list.clear()

        if (ch) {
            when (index) {

                0 -> {

                    list.add("Andijon shahri")

                }

                1 -> {

                    list.add("Buxoro shahri")
                    list.add("Olot tumani")

                }

                2 -> {

                    list.add("Farg'ona shahri")

                }

                3 -> {

                    list.add("Jizzax shahri")

                }

                5 -> {

                    list.add("Qarshi shahri")
                    list.add("Shahrizabz")

                }

                4 -> {

                    list.add("Mang'it tumani")

                }

                6 -> {

                    list.add("Xiva shahri")
                    list.add("Urganch tumani")
                }

                7->{

                    list.add("Guliston shahri")
                    list.add("Pop tumani")


                }

                8 -> {

                    list.add("Navoiy shahri")
                    list.add("Qiziltepa tumani")

                }

                9 -> {

                    list.add("Kadan ")
                    list.add("Jo'sh")
                    list.add("Kattaqo'rg'on shahri")
                    list.add("Samarqand shahri")

                }

                10 -> {

                    list.add("Sirdaryo shahri")


                }

                11 -> {

                    list.add("Jarqo'rg'on tumani")
                    list.add("Denov")
                    list.add("Termiz shahri")

                }

                12 -> {

                    list.add("Toshkent shahri")

                }


            }

//            if(repons.size==2){
//
//                val intent= Intent(this,HomeActivity::class.java)
//                intent.putExtra("Vil",repons.get(0))
//                intent.putExtra("reg",repons.get(1))
//                startActivity(intent)
//                finish()
//
//            }

        } else {

            list.add("Andijon viloyati")
            list.add("Buxoro viloyati")
            list.add("Farg'ona viloyati")
            list.add("Jizzax viloyati")
            list.add("Qaraqalpog'iston respublikasi")
            list.add("Qashqadaryo viloyati")
            list.add("Xorazm viloyati")
            list.add("Namangan viloyati")
            list.add("Navoiy viloyati")
            list.add("Samarqand viloyati")
            list.add("Sirdaryo viloyati")
            list.add("Surxondaryo viloyati")
            list.add("Tashkent viloyati")

            repons.clear()

            Log.e("Response", repons.toString())

        }
        adapter.notifyDataSetChanged()

    }

    private fun saveStringsToPreferences(key1:String, key2:String, string1: String, string2: String,PREF_NAME:String) {
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key1, string1)
        editor.putString(key2, string2)
        editor.apply()
    }


}