package com.example.busbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.busbooking.databinding.ActivityMembersBinding
import com.example.busbooking.model.endModel

class MembersActivity : AppCompatActivity() {

    lateinit var binding:ActivityMembersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMembersBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val data = intent.getSerializableExtra("mydata2") as endModel




    }
}