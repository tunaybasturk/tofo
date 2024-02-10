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
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tofo.databinding.ActivitySecimBinding
import com.google.android.flexbox.AlignContent
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.Locale.filter

class Secim : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: SecimlerAdapter
    private lateinit var binding : ActivitySecimBinding
    private var SecimlerList : ArrayList<String> = ArrayList()
    private val CAMERA_PERMISSION_REQUEST_CODE = 123
    private val REQUEST_CODE = 42
    private val FILE_NAME = "photo.jpg"
    private lateinit var photoFile : File
    private var secilenlerList : ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecimBinding.inflate(layoutInflater)
        setContentView(binding.root)



        recyclerViewAdapter = SecimlerAdapter(this)
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.alignItems = AlignItems.STRETCH
        binding.recyclerView.layoutManager =layoutManager
        binding.recyclerView.adapter =recyclerViewAdapter
        //recyclerViewAdapter.guncelle_secım_list(SecimlerList)
        listeyi_guncelle()
        recyclerViewAdapter.guncelle_secım_list(SecimlerList)
        recyclerViewAdapter.size_esitleme()

        veri_ara()



        binding.kameraIcon.setOnClickListener {
            requestCameraPermission()
        }

        binding.secimBitir.setOnClickListener{
            binding.aramaYapma.text.clear()
            secilenlerList = recyclerViewAdapter.secilenleri_getir()
            recyclerViewAdapter.guncelle_secım_list(SecimlerList)
            Log.d("SATURN",secilenlerList.size.toString())
            for(i in secilenlerList){
                Log.d("MARS ",i)
            }

            recyclerViewAdapter.secimlersecildiList.fill(0)
            recyclerViewAdapter.secimDurumlari.fill(false)
            recyclerViewAdapter.secilenlerList.fill("")


            binding.secimBitir.text = "Tümünü Gör"
        }





    }


   fun veri_ara(){
        binding.aramaYapma.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?,start: Int, before: Int, count: Int) {
                /*here we add filter*/
                filtreleme(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
   }

    fun listeyi_guncelle(){
        SecimlerList.add("Taze Köy Sütü")
        SecimlerList.add("Köy Yumurtası")
        SecimlerList.add("Kavurma")
        SecimlerList.add("Yeşil Zeytin")
        SecimlerList.add("Boyoz")
        SecimlerList.add("Türk Kahvesi")
        SecimlerList.add("Çerkez Peyniri")
        SecimlerList.add("Basmati Pirinç")
        SecimlerList.add("Et Sote")
        SecimlerList.add("Beşemel Soslu Tavuk")
        SecimlerList.add("Taze Mantar")

    }

    fun filtreleme(text:String){

        val filtrelenmis_liste = ArrayList<String>()

        for(item in SecimlerList){

            if(item.lowercase().contains(text.lowercase())){
                filtrelenmis_liste.add(item)
            }

        }
        recyclerViewAdapter.filtrelenmisliste(filtrelenmis_liste)

        if(filtrelenmis_liste.isEmpty()){
            Toast.makeText(this,"Arama Bulunamadı",Toast.LENGTH_SHORT).show()
        }

    }



/*
    fun updateSecimBitirTextView(secimDurumlari: ArrayList<Boolean>, activity: Secim) {
            for (i in 0 until secimDurumlari.size) {
                if (secimDurumlari[i]) {
                    activity.binding.secimBitir.text = "Seçimleri Bitir"
                    return
                }
            }
            activity.binding.secimBitir.text = "Tümünü Gör"
        }
*/

    fun updateSecimBitirTextView(secimDurumlari: ArrayList<Boolean>) {
        if(secimDurumlari.contains(true)){
            binding.secimBitir.text = "Seçimleri Bitir"
        }
        else {
            binding.secimBitir.text = "Tümünü Gör"
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