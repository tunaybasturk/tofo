package com.example.tofo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tofo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.kayitOl.setOnClickListener{
            intent = Intent(this,Kayit_Ol::class.java)
            startActivity(intent)
        }

        binding.sifremiUnuttum.setOnClickListener {
            intent = Intent(this,Sifremi_unuttum::class.java)
            startActivity(intent)
        }

        binding.girisYap.setOnClickListener {
            intent = Intent(this,Anasayfa::class.java)
            startActivity(intent)
        }


    }
}