package com.example.perludilindungi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perludilindungi.databinding.ActivityMainBinding
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
        fab.setOnClickListener{
            val intent = Intent(this, Qrcode::class.java)
            startActivity(intent)
        }
//        setContentView(R.layout.activity_main)
//        val textView: TextView = findViewById(R.id.textView)
//        val qrButton: ImageButton = findViewById(R.id.qr_button)
//        qrButton.setOnClickListener {
//            val intentIntegrator = IntentIntegrator(this)
//            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
//            intentIntegrator.initiateScan()
//        }
    }
}