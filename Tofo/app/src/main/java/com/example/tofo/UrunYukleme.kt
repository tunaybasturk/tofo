package com.example.tofo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.tofo.databinding.ActivityUrunYuklemeBinding
import java.io.File

class UrunYukleme : AppCompatActivity() {

    private lateinit var  binding : ActivityUrunYuklemeBinding
    private val CAMERA_PERMISSION_REQUEST_CODE = 123
    private val REQUEST_CODE = 42
    private val FILE_NAME = "photo.jpg"
    private lateinit var photoFile : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrunYuklemeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bildirimler.setOnClickListener{
            intent = Intent(this,Bildirimler::class.java)
            startActivity(intent)
        }


        binding.tekrarCek.setOnClickListener{
            binding.fiyat.text.clear()
            binding.baslik.text.clear()
            requestCameraPermission()
        }

        val intentgelenveri = intent.getByteArrayExtra("resim")
        var bitmap: Bitmap? = null // nullable olarak tanımlandı

        if (intentgelenveri != null) {
            bitmap = byteArrayToBitmap(intentgelenveri)
            binding.urun.setImageBitmap(bitmap)
        }

        // if bloğu dışında kullanılacaksa null kontrolü yapmanız gerekebilir
        if (bitmap != null) {
            binding.urun.setImageBitmap(bitmap)
        }
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.urun.setImageBitmap(imageBitmap)

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
            Toast.makeText(this,"Kamera Açılamıyor", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getPhotoFile(fileName: String): File {

        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDirectory)


    }
}