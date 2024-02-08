package com.example.busbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.busbooking.adapters.PlaceAdapter
import com.example.busbooking.databinding.ActivityPlaceBinding
import com.example.busbooking.model.endModel
import com.example.busbooking.model.tickets

class PlaceActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlaceBinding
    lateinit var adapter: PlaceAdapter
    var num = 0;
    lateinit var list: ArrayList<Int>
    lateinit var list2:ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlaceBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val data = intent.getSerializableExtra("myData") as tickets


        Log.e("myDate", data.toString())

        list = ArrayList()
        list2= ArrayList()
        val arrayList: ArrayList<Int> = ArrayList()

        for (i in 1..70) {
            arrayList.add(i)
        }

        binding.apBack.setOnClickListener {

            finish()

        }

        binding.btnContinue.setOnClickListener {

            if(num!=0){
                data.count=num

                val data2=endModel(data,num,list)

                val intent=Intent(this@PlaceActivity, PayingActivity::class.java)

                intent.putExtra("mydata2",data2)

                startActivity(intent)
            }

        }
        adapter = PlaceAdapter(arrayList, object : PlaceAdapter.ItemSetOnClickListener {
            override fun onClick(data: Int, ch: Boolean) {
                if (ch) {
                    num++
                    list.add(data)

                    Log.e("aaabbb", data.toString())

                    binding.tvTn.text = "Tanlangan $num"

                } else {

                    if (num > 0) {
                        num--
                    } else {
                        num = 0
                    }
                    list.remove(data)
                    binding.tvTn.text = "Tanlangan $num"

                }
            }


        })

        binding.apRecycler.adapter = adapter


    }


}