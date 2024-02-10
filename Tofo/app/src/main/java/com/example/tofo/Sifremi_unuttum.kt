package com.example.tofo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tofo.databinding.ActivitySifremiUnuttumBinding

class Sifremi_unuttum : AppCompatActivity() {

    lateinit var  binding : ActivitySifremiUnuttumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifremiUnuttumBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.girisYap.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}