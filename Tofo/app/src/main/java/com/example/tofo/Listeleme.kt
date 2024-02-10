package com.example.tofo

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tofo.databinding.ActivityListelemeBinding
import java.io.ByteArrayOutputStream
import java.io.File

class Listeleme : AppCompatActivity() {

    lateinit var binding : ActivityListelemeBinding
    private lateinit var UrunList : ArrayList<Urun>
    private lateinit var ListelemeAdapter:ListelemeAdapter
    private val CAMERA_PERMISSION_REQUEST_CODE = 123
    private val REQUEST_CODE = 42
    private val FILE_NAME = "photo.jpg"
    private lateinit var photoFile : File
    private var bildirim :Int = 0
    private val bildirim_tiklanildi_renk = R.color.bildirim_tiklanildi_circle_color
    private val bildirim_tiklanilmadi_renk = R.color.bildirim_tiklanilmadi_circle_color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListelemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UrunList = ArrayList()
        ListelemeAdapter = ListelemeAdapter(UrunList,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ListelemeAdapter
        listeyi_guncelle()
        veri_ara()

        binding.kameraIcon.setOnClickListener{
            requestCameraPermission()
        }

        binding.kullaniciIcon.setOnClickListener{
            intent = Intent(this,Filtreler::class.java)
            startActivity(intent)
        }

        binding.aramaIcon.setOnClickListener{
            intent = Intent(this,Secim::class.java)
            startActivity(intent)
        }

        binding.bildirimCircle.setOnClickListener{
            if(bildirim == 0){
                binding.bildirimCircle.setColorFilter(ContextCompat.getColor(this,bildirim_tiklanildi_renk))
                bildirim=1
            }
            else if(bildirim == 1){
                binding.bildirimCircle.setColorFilter(ContextCompat.getColor(this,bildirim_tiklanilmadi_renk))
                bildirim=0
            }

        }



    }


    fun veri_ara(){
        binding.aramaYapma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?,start: Int, before: Int, count: Int) {

                filtreleme(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun listeyi_guncelle(){
        UrunList.add(Urun(R.drawable.kozde_turk_kahvesi,"Közde Türk Kahvesi", 3,11,"2 kilometre",R.drawable.anasayfa_icecekler,"₺7,5"))
        UrunList.add(Urun(R.drawable.ibrahim_bey_turk_kahvesi,"İbrahim Türk Kahvesi", 4,40,"500 metre",R.drawable.anasayfa_mamuller,"₺11"))
        UrunList.add(Urun(R.drawable.osmanli_kahvesi,"Osmanlı Kahvesi", 5,5,"760 metre",R.drawable.anasayfa_mamuller,"₺9"))
        UrunList.add(Urun(R.drawable.feda_turk_kahve,"Feda Türk", 4,80,"50 metre",R.drawable.anasayfa_mamuller,"₺15"))
        UrunList.add(Urun(R.drawable.naneli_turk_kahvesi,"Naneli Türk Kahvesi", 2,1,"2 kilometre",R.drawable.anasayfa_icecekler,"₺10"))
    }

    fun filtreleme(text:String){
        val filtrelenmis_liste = ArrayList<Urun>()

        for(item in UrunList){
            if(item.urun_isim.lowercase().contains(text.lowercase())){
                filtrelenmis_liste.add(item)
            }
        }

        ListelemeAdapter.filtrelenmisliste(filtrelenmis_liste)

        if(filtrelenmis_liste.isEmpty()){
            Toast.makeText(this,"Arama Bulunamadı", Toast.LENGTH_SHORT).show()
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
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }


}