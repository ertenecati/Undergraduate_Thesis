package com.ertenecati

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navigationController = navHostFragment.findNavController()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setupWithNavController(navigationController)
        getPermissions()
    }

    private fun getPermissions() {
        val locationResult = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val locationIsGranted = locationResult == PackageManager.PERMISSION_GRANTED
        val audioResult = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        val audioIsGranted = audioResult == PackageManager.PERMISSION_GRANTED

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val bluetoothScan = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
            val scanIsGranted = bluetoothScan == PackageManager.PERMISSION_GRANTED
            val bluetoothConnect = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
            val connectIsGranted = bluetoothConnect == PackageManager.PERMISSION_GRANTED
            val bluetoothAdvertise = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE)
            val advertiseIsGranted = bluetoothAdvertise == PackageManager.PERMISSION_GRANTED

            if (!locationIsGranted && !audioIsGranted && !scanIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !scanIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !scanIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !scanIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !scanIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !scanIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !scanIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !scanIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !scanIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !scanIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!scanIsGranted && !connectIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !audioIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !scanIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_SCAN)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !scanIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_SCAN)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!scanIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!scanIsGranted && !advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!advertiseIsGranted && !connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_ADVERTISE, Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!scanIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_SCAN)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!connectIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!advertiseIsGranted) {
                val permissionList = arrayOf(Manifest.permission.BLUETOOTH_ADVERTISE)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            }
        } else {
            if (!locationIsGranted && !audioIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!locationIsGranted) {
                val permissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            } else if (!audioIsGranted) {
                val permissionList = arrayOf(Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(this, permissionList, 1001)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}