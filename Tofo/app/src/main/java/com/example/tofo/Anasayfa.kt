package com.example.tofo

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.tofo.databinding.ActivityAnasayfaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class Anasayfa : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityAnasayfaBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private lateinit var sharedPreferences : SharedPreferences
    var trackBoolean : Boolean? = null
    val REQUEST_IMAGE_CAPTURE = 1
    private val CAMERA_PERMISSION_REQUEST_CODE = 123
    private val REQUEST_CODE = 42
    private val FILE_NAME = "photo.jpg"
    private lateinit var photoFile : File



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnasayfaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()

        sharedPreferences = this.getSharedPreferences("com.example.tofo", MODE_PRIVATE)
        trackBoolean=false

        binding.aramaIcon.setOnClickListener{
            intent = Intent(this,Secim::class.java)
            startActivity(intent)
        }

        binding.bildirimler.setOnClickListener {
            intent = Intent(this,Bildirimler::class.java)
            startActivity(intent)
        }

        binding.kullaniciIcon.setOnClickListener {
            intent = Intent(this,Filtreler::class.java)
            startActivity(intent)
        }

        binding.kameraIcon.setOnClickListener {
            requestCameraPermission()
        }

        binding.anasayfaIcon.setOnClickListener{
            intent = Intent(this,Listeleme::class.java)
            startActivity(intent)
        }

    }





    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //40.763140823388625, 29.922891455649268
        // Add a marker in Sydney and move the camera
        /*
        val izmit = LatLng(40.763140823388625, 29.922891455649268)
        mMap.addMarker(MarkerOptions().position(izmit).title("Gamino"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(izmit,15f))
        */

        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {

                trackBoolean = sharedPreferences.getBoolean("trackBoolean",false)

                if(trackBoolean == false){
                    val userlocation = LatLng(location.latitude,location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,15f))
                    sharedPreferences.edit().putBoolean("trackBoolean",true).apply()
                }


            }
            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        }


        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.root,"Konum için izin gerekli",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver"){

                    permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()
            }
            else{
                permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            val lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            if(lastlocation != null){
                val lastUserlocation = LatLng(lastlocation.latitude,lastlocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserlocation,15f))
            }
            mMap.isMyLocationEnabled = true


        }




    }

    private fun registerLauncher(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){result ->
            if(result){
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                    val lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if(lastlocation != null){
                        val lastUserlocation = LatLng(lastlocation.latitude,lastlocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserlocation,15f))
                    }
                    mMap.isMyLocationEnabled = true

                }
            }
            else{
                Toast.makeText(this@Anasayfa,"İzin Gerekli",Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun openCameraForImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

        val fileProvider = FileProvider.getUriForFile(this,"com.example.tofo.fileprovider",photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }
        else{
            Toast.makeText(this,"Kamera Açılamıyor",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPhotoFile(fileName: String): File {

        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDirectory)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
            val byteArray :ByteArray
            byteArray = bitmapToByteArray(imageBitmap)
            Log.d("SATURN","AAAAA GELDİİİ")
            intent = Intent(this,UrunYukleme::class.java)
            intent.putExtra("resim",byteArray)
            startActivity(intent)
            Log.d("SATURN","bbbbb GELDİİİ")

        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCameraForImage()
            } else {
                Toast.makeText(this@Anasayfa, "Kamera izni reddedildi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
           openCameraForImage()
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

}