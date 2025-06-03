package com.ertenecati

import android.bluetooth.BluetoothSocket
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var socket: BluetoothSocket? = null

    fun sendCommand(command: String) {
        socket?.outputStream?.write(command.toByteArray())
    }

}