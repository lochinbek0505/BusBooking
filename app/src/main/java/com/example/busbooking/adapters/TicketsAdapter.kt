package com.example.busbooking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.busbooking.R
import com.example.busbooking.databinding.ArRecyclerItemLayoutBinding
import com.example.busbooking.model.tickets


class TicketsAdapter(
    var items: ArrayList<tickets>,
    var listener: ItemSetOnClickListener,
) :
    RecyclerView.Adapter<TicketsAdapter.Holder>() {


    interface ItemSetOnClickListener {
        fun onClick(data: tickets)
    }

    inner class Holder(var view: ArRecyclerItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: tickets) {

            view.apply {

//                Glide.with(itemView.context)
//                    .load(data.photo)
//                    .into(this.mhlIv1)

//                this.mhlTv.text = data.name
                this.tvLocation.text="${data.from} - ${data.to}"
                this.tvPrice.text="${data.price} so'm"
                this.tvTime.text=data.time
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ArRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(
            binding
        )


    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        holder.bind(item)


        holder.itemView.setOnClickListener {


                listener.onClick(item)

            }
        }





    override fun getItemCount(): Int = items.count()

}