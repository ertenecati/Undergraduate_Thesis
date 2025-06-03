package com.ertenecati.presentation

import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ertenecati.MainViewModel
import com.ertenecati.R
import com.ertenecati.data.Device
import com.ertenecati.databinding.FragmentBluetoothBinding
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("MissingPermission")
class BluetoothFragment : Fragment(R.layout.fragment_bluetooth), IBluetooth {

    companion object {
        val deviceUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var connectedDeviceAddress: String? = null
        var connected = false
        var connectedDeviceName: String? = null
    }

    private lateinit var binding: FragmentBluetoothBinding
    private lateinit var viewModel: MainViewModel
    private var bluetoothAdapter: BluetoothAdapter? = null
    private val deviceList: ArrayList<Device> = arrayListOf()
    private val pairedDeviceList: ArrayList<Device> = arrayListOf()
    private val adapter = BluetoothDeviceAdapter(this)
    private val countDownTimer = object : CountDownTimer(12000, 1000) {
        override fun onTick(p0: Long) {}
        override fun onFinish() {
            binding.tvScanner.isClickable = true
            binding.tvScanner.isFocusable = true
            binding.tvScanner.text = "Cihaz Ara"
            binding.progressBar.visibility = View.GONE
            adapter.list = pairedDeviceList + deviceList
            stopSearch()
        }
    }
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    deviceList.add(Device(device?.name, device?.address))
                    adapter.list = pairedDeviceList + deviceList
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBluetoothBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.tvScanner.setOnClickListener {
            deviceList.clear()
            pairedDeviceList.clear()
            binding.tvScanner.isClickable = false
            binding.tvScanner.isFocusable = false
            binding.progressBar.visibility = View.VISIBLE
            countDownTimer.start()
            binding.tvScanner.text = "Aranıyor..."
            startSearch()
        }
        binding.tvDisconnect.setOnClickListener {
            disconnect()
            binding.clConnected.visibility = View.GONE
        }
        if (connected) {
            binding.clConnected.visibility = View.VISIBLE
            binding.tvConnectedDevice.text = connectedDeviceName
        }
    }

    override fun onDestroyView() {
        stopSearch()
        super.onDestroyView()
    }

    private fun startSearch() {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        activity?.registerReceiver(receiver, filter)
        val bluetoothManager: BluetoothManager = requireActivity().getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter
        if (bluetoothAdapter?.isEnabled == true) {
            bluetoothAdapter?.startDiscovery()
            val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address
                pairedDeviceList.add(Device(deviceName, deviceHardwareAddress))
            }

        } else {
            val bluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(bluetoothIntent)
        }
    }

    private fun stopSearch() {
        if (bluetoothAdapter?.isDiscovering == true) {
            bluetoothAdapter?.cancelDiscovery()
        }
        try {
            activity?.unregisterReceiver(receiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDeviceClick(device: Device) {
        if (connected) return
        val btDevice = bluetoothAdapter?.getRemoteDevice(device.deviceMac)
        try {
            Runnable {
                viewModel.socket = btDevice?.createInsecureRfcommSocketToServiceRecord(deviceUUID)
                viewModel.socket?.connect()
            }.run()
            connected = true
        } catch (e: Exception) {
            connected = false
            e.printStackTrace()
        } finally {
            if (connected) {
                connectedDeviceAddress = device.deviceMac
                val temp = "${btDevice?.name} Bağlandı"
                connectedDeviceName = btDevice?.name
                binding.tvConnectedDevice.text = temp
                binding.clConnected.visibility = View.VISIBLE
            } else {
                binding.clConnected.visibility = View.GONE
            }
        }
    }

    private fun disconnect() {
        viewModel.socket?.let { socket ->
            try {
                socket.close()
                viewModel.socket = null
                connectedDeviceAddress = null
                connected = false
                connectedDeviceName = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}