package com.example.busbooking.model

import java.io.Serializable

data class endModel(

    val tickets: tickets,
    val count: Int,
    val list: ArrayList<Int>

):Serializable
