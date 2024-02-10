package com.example.tofo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tofo.databinding.ActivityKullaniciProfiliBinding

class Kullanici_Profili : AppCompatActivity() {

    private lateinit var  binding : ActivityKullaniciProfiliBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKullaniciProfiliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kullaniciProfiliBildirimler.setOnClickListener {
            intent = Intent(this,Bildirimler::class.java)
            startActivity(intent)
        }
        binding.kullaniciProfiliGeriIcon.setOnClickListener {
            intent = Intent(this,Anasayfa::class.java)
            startActivity(intent)
        }

    }
}