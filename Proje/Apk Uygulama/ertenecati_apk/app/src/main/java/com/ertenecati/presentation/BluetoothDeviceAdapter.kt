package com.ertenecati.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ertenecati.data.Device
import com.ertenecati.databinding.ListItemBluetoothDeviceBinding

class BluetoothDeviceAdapter(val listener: IBluetooth) : RecyclerView.Adapter<BluetoothDeviceAdapter.ViewHolder>() {

    var list: List<Device> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ListItemBluetoothDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            if (device.deviceName.isNullOrEmpty()) {
                binding.tvDeviceName.text = "Device Name:"
            } else {
                binding.tvDeviceName.text = "Device Name: ${device.deviceName}"
            }
            if (device.deviceMac.isNullOrEmpty()) {
                binding.tvDeviceMac.text = "Device Address:"
            } else {
                binding.tvDeviceMac.text = "Device Address: ${device.deviceMac}"
            }
            binding.root.setOnClickListener {
                listener.onDeviceClick(device)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBluetoothDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}