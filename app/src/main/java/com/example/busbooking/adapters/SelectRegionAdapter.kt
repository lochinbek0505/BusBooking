package com.example.busbooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.busbooking.databinding.AsrRecyclerLayoutBinding


class SelectRegionAdapter(
    var items: MutableList<String>,
    var listener: ItemSetOnClickListener,
) :
    RecyclerView.Adapter<SelectRegionAdapter.Holder>() {

    var nom = 0

    interface ItemSetOnClickListener {
        fun onClick(data: String, position: Int)
    }

    inner class Holder(var view:AsrRecyclerLayoutBinding ) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: String) {

            view.apply {



                this.tvRegion.text = data
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            AsrRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(
            binding
        )


    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        holder.bind(item)


        holder.itemView.setOnClickListener {


                listener.onClick(item,position)


        }
    }

    fun check(a: Int): Boolean {

        val arr = arrayListOf<Int>(3, 8, 13, 18, 23, 28, 33, 38, 43, 48, 53, 58, 63, 64, 65)

        if (arr.contains(a)) {

            return false

        } else {
            return true
        }


    }


    override fun getItemCount(): Int = items.count()

}