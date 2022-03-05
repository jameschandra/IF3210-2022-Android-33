package com.example.perludilindungi.ui.qrcode

import android.hardware.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.ActivityQrcodeBinding

class Qrcode : AppCompatActivity(), SensorEventListener {
    private var suhu: Sensor? = null
    private lateinit var sensorManager: SensorManager
    private lateinit var binding: ActivityQrcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        suhu = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        if (sensorEvent != null) {
            binding.derajat.text = "${sensorEvent.values[0].toString()}°"
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // NO IMPLEMENTATION
    }

//    override fun onFlushCompleted(p0: Sensor?) {
//        // NO IMPLEMENTATION
//    }

    override fun onResume() {
        // Register a listener for the sensor.
        sensorManager.registerListener(this, suhu, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}