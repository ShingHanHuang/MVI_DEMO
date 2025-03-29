package com.example.mvi_demo.adapter

import com.example.mvi_demo.data.CarParkInfo
import com.example.mvi_demo.databinding.CarParkAdapterItemBinding
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CarParkInfoAdapter(private var context: Context) :
    RecyclerView.Adapter<CarParkInfoAdapter.ViewHolder>() {
    private var data: ArrayList<CarParkInfo> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CarParkAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(position)
    }

    fun setCarParkInfoList(data: ArrayList<CarParkInfo>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: CarParkAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(position: Int) {
            Log.e("peter", "data:${data[position].id}")
            binding.carParkId.text = String.format("ID:%s", data[position].id)
            binding.carParkAddress.text = String.format("Addresss:%s", data[position].address)
            binding.carParkName.text = String.format("Name:%s", data[position].name)
            binding.carParkTotalCar.text = String.format("TotalCar:%s", data[position].totalcar)
            binding.carParkTotalAvailablecar.text =
                String.format("AvailableCar:%s", data[position].availablecar)
            data[position].ChargeStation?.let {
                binding.carParkTotalStandby.visibility = View.VISIBLE
                binding.carParkTotalCharge.visibility = View.VISIBLE
                binding.carParkTotalStandby.text =
                    String.format("待機中總數:%s", data[position].standBy)
                binding.carParkTotalCharge.text =
                    String.format("充電中總數:%s", data[position].charge)
            } ?: let {
                binding.carParkTotalStandby.visibility = View.GONE
                binding.carParkTotalCharge.visibility = View.GONE
            }
        }
    }

}