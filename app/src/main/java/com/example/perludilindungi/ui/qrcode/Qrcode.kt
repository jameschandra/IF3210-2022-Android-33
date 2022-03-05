package com.example.perludilindungi.ui.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.*
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.budiyev.android.codescanner.*
import com.budiyev.android.codescanner.ErrorCallback.SUPPRESS
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.perludilindungi.R
import com.example.perludilindungi.api.RetrofitInstance
import com.example.perludilindungi.databinding.ActivityQrcodeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class Qrcode : AppCompatActivity(), SensorEventListener2 {
    private var suhu: Sensor? = null
    private lateinit var sensorManager: SensorManager
    private lateinit var binding: ActivityQrcodeBinding
    private lateinit var codeScanner: CodeScanner
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lon : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        suhu = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getUserLocation()

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(this, scannerView!!)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()

                val reqBodyObject = JSONObject()
                reqBodyObject.put("qrCode", it.text)
                reqBodyObject.put("latitude", lat.toString())
                reqBodyObject.put("longitude", lon.toString())

                val httpReqBody =
                    reqBodyObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

                CoroutineScope(Dispatchers.IO).launch {
                    // Do the POST request and get response
                    val res =
                        RetrofitInstance.checkInInstance.postCheckIn(requestBody = httpReqBody)

                    withContext(Dispatchers.Main) {
                        if (res.isSuccessful) {
                            val resParse = res.body()
                            if (resParse?.success == true) {
                                Log.d("Hasil", resParse.data.toString())
                                binding.status.setText(resParse.data.userStatus)

                                if (resParse.data.userStatus == "green") {
                                    binding.keterangan.setText("Keterangan: OK!")
                                } else {
                                    binding.keterangan.setText(resParse.data.reason)
                                }
                            }
                        }
                    }
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
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

    override fun onFlushCompleted(p0: Sensor?) {
        // NO IMPLEMENTATION
    }

    override fun onResume() {
        sensorManager.registerListener(this, suhu, SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    private fun getUserLocation() {
        Log.d("FASKES", "IN SEARCH FASKES")
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

                // No permission for location
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location == null) {
//                Location null
            } else {
                lat = location.latitude
                lon = location.longitude
            }
        }

    }
} // list of type BarcodeFormat,
// ex. listOf(BarcodeFormat.QR_COD