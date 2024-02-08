package com.example.busbooking.model

import android.os.Parcelable
import java.io.Serializable


data class tickets(

    val from: String,
    val to: String,
    val date: String,
    val price: Int,
    val time: String,
    var count: Int=0,
    var name:String ="",
    var phone:String =""

) : Serializable
