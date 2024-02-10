package com.example.tofo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tofo.databinding.ActivityKayitOlBinding

class Kayit_Ol : AppCompatActivity() {

    lateinit var binding : ActivityKayitOlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKayitOlBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.girisYap.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}