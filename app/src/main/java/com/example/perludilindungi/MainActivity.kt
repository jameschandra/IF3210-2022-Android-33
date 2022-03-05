package com.example.perludilindungi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perludilindungi.databinding.ActivityMainBinding
import com.example.perludilindungi.ui.qrcode.QrFragment
import com.example.perludilindungi.ui.qrcode.Qrcode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_news, R.id.navigation_bookmark
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val fab = binding.fab
        fab.setOnClickListener {
            val intent = Intent(this, Qrcode::class.java)
            startActivity(intent)
        }

        getPermission()
//        setContentView(R.layout.activity_main)
//        val textView: TextView = findViewById(R.id.textView)
//        val qrButton: ImageButton = findViewById(R.id.qr_button)
//        qrButton.setOnClickListener {
//            val intentIntegrator = IntentIntegrator(this)
//            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
//            intentIntegrator.initiateScan()
//        }
    }

    private fun getPermission() {
        while (!checkPermissions()) {
            requestPermission()
        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
            ),
            1
        )
    }
}