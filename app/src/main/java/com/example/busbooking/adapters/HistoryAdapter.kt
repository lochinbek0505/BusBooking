package com.example.busbooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.busbooking.databinding.FdRecyclerItemLayoutBinding
import com.example.busbooking.model.tickets


class HistoryAdapter(
    var items: List<tickets>
) :
    RecyclerView.Adapter<HistoryAdapter.Holder>() {

//
//    interface ItemSetOnClickListener {
//        fun onClick(data: tickets)
//    }

    inner class Holder(var view: FdRecyclerItemLayoutBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: tickets) {

            view.apply {

//                Glide.with(itemView.context)
//                    .load(data.photo)
//                    .into(this.mhlIv1)

//                this.mhlTv.text = data.name
//                this.tvLocation.text="${data.from} - ${data.to}"
//                this.tvPrice.text="${data.price} so'm"
//                this.tvTime.text=data.time
                this.tvName.text = data.name
                this.tvPhone.text = data.phone
                this.tvFrom.text = data.from
                this.tvTo.text = data.to
                this.tvDate.text = data.date
                this.tvTime.text = data.time
                this.tvCount.text = data.count.toString()
                this.tvPrice.text = data.price.toString() + " so'm"
                this.tvSumma.text = "${data.count * data.price} so'm"


            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            FdRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(
            binding
        )


    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]

        holder.bind(item)


//        holder.itemView.setOnClickListener {
//
//
//            listener.onClick(item)
//
//        }
    }


    override fun getItemCount(): Int = items.count()

}