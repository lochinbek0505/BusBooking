package com.example.busbooking

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.example.busbooking.databinding.ActivityPayingBinding
import com.example.busbooking.model.endModel
import com.example.busbooking.utils.TicketDatabaseHelper
import kotlin.time.times

class PayingActivity : AppCompatActivity() {

    lateinit var binding:ActivityPayingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPayingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dbHelper = TicketDatabaseHelper(this)
        val data = intent.getSerializableExtra("mydata2") as endModel



        val price=data.count*data.tickets.price

        binding.tvChainCount.text= data.count.toString()
        binding.tvChains.text=" $price so'm"

        binding.ivApBack.setOnClickListener {

            finish()

        }
        binding.btnPay.setOnClickListener {

            var name=binding.etCardDate.text
            var number=binding.etCardNumber.text

            if(!name.isNullOrEmpty() and !number.isNullOrEmpty()) {
                data.tickets.name = name.toString()
                data.tickets.phone = number.toString()
                Log.e("testt", data.tickets.toString())
                dbHelper.addTicket(data.tickets)



                customToast(" Buyurtma muofaqqiyatli qabul qilindi")
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
            else{

                Toast.makeText(this,"Iltimos maydonni to'liq to'ldiring", Toast.LENGTH_LONG).show()

            }
        }


    }

    fun Context.customToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        val toast = Toast.makeText(this, "", duration)
        val textView = TextView(this)

        // Set text
        textView.text = message

        // Set padding
        textView.setPadding(16, 16, 16, 16)

        // Set text color
        textView.setTextColor(Color.GREEN)

        // Set background color and corner radius
        val drawable = GradientDrawable()
//        drawable.setColor(Color.WHITE)
        drawable.cornerRadius = 16f
        textView.background = drawable

        // Set text size
        textView.textSize = 18f

        // Set text alignment
        textView.gravity = Gravity.BOTTOM

        toast.view = textView
//        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }



}