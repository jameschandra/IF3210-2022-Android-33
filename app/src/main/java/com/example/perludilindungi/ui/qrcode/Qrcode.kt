package com.example.perludilindungi.ui.qrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perludilindungi.databinding.ActivityQrcodeBinding

class Qrcode : AppCompatActivity() {
    private lateinit var binding: ActivityQrcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}