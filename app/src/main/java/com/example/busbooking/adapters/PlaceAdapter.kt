package com.example.busbooking.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.busbooking.R
import com.example.busbooking.databinding.ApRecyclerItemLayoutBinding


class PlaceAdapter(
    var items: ArrayList<Int>,
    var listener: ItemSetOnClickListener,
) :
    RecyclerView.Adapter<PlaceAdapter.Holder>() {

    var nom = 0

    interface ItemSetOnClickListener {
        fun onClick(data: Int, ch: Boolean)
    }

    inner class Holder(var view: ApRecyclerItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: Int, check: Boolean) {

            view.apply {

//                Glide.with(itemView.context)
//                    .load(data.photo)
//                    .into(this.mhlIv1)

//                this.mhlTv.text = data.name
                if (check) {

                    this.rlApr.visibility = View.INVISIBLE

                }
                if (data == 1 || data == 2) {
                    this.rlApr.setBackgroundResource(R.drawable.ic_chair2)
                }
                this.tvApr.text = data.toString()
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ApRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(
            binding
        )


    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

//        holder.bind(item)

        val rL: RelativeLayout = holder.itemView.findViewById(R.id.rl_apr)
        if (check(item)) {
            nom++
            holder.bind(nom, false)
        } else {

            holder.bind(0, true)
        }
        var ch = true

        holder.itemView.setOnClickListener {

            if (position > 1) {
                if (ch) {
                    rL.setBackgroundResource(R.drawable.ic_chair3)
                    listener.onClick(nom, ch)
                    ch = false

                } else {
                    rL.setBackgroundResource(R.drawable.ic_chair)
                    listener.onClick(nom, ch)
                    Log.e("aaabbbb", nom.toString())

                    ch = true
                }
            } else {

                listener.onClick(0, false)

            }
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