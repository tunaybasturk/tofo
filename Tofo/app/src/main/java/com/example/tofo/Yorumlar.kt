package com.example.tofo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tofo.databinding.ActivityYorumlarBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class Yorumlar : AppCompatActivity() {


    private lateinit var binding : ActivityYorumlarBinding
    lateinit var YorumList : ArrayList<Yorum>
    lateinit var yorumlarAdapter: YorumlarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYorumlarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val simdikiTarihSaat = dateFormat.format(Date())

        Log.d("SATURN",simdikiTarihSaat.toString())

        val gelenIntent = intent
        val urun_isim = gelenIntent.getStringExtra("ürün_isim")
        val urun_yildiz = gelenIntent.getIntExtra("ürün_yildiz",0)
        val urun_fiyat= gelenIntent.getStringExtra("ürün_fiyat")
        val urun_yorum = gelenIntent.getStringExtra("ürün_yorum")
        val urun_resim = gelenIntent.getIntExtra("ürün_resim",0)


        binding.urunFiyat.setText(urun_fiyat)
        binding.urunIsmi.setText(urun_isim)
        binding.kullaniciResim.setImageResource(urun_resim)

        if(urun_yildiz == 1){
            binding.yildiz4.visibility = View.GONE
            binding.yildiz3.visibility = View.GONE
            binding.yildiz2.visibility = View.GONE
            binding.yildiz1.visibility = View.GONE
            binding.puanOrtalama.setText("$urun_yildiz Puan ortalamalı lezzet")
        }
        if(urun_yildiz == 2){
            binding.yildiz3.visibility = View.GONE
            binding.yildiz2.visibility = View.GONE
            binding.yildiz1.visibility = View.GONE
            binding.puanOrtalama.setText("$urun_yildiz Puan ortalamalı lezzet")
        }
        if(urun_yildiz == 3){
            binding.yildiz2.visibility = View.GONE
            binding.yildiz1.visibility = View.GONE
            binding.puanOrtalama.setText("$urun_yildiz Puan ortalamalı lezzet")
        }
        if(urun_yildiz == 4){
            binding.yildiz1.visibility = View.GONE
            binding.puanOrtalama.setText("$urun_yildiz Puan ortalamalı lezzet")
        }
        else{
            binding.puanOrtalama.setText("$urun_yildiz Puan ortalamalı lezzet")
        }



        YorumList = ArrayList()
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN","Çok yerde kahve içtim ama bu kadar lezzetlisine denk gelmedim. Üstelik paket servis veriyorlar. Sanırım artık buranın bağımlısı olduk. Tebrik ederim harikasınız."))
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN","Çok yerde kahve içtim ama bu kadar lezzetlisine denk gelmedim. Üstelik paket servis veriyorlar. Sanırım artık buranın bağımlısı olduk. Tebrik ederim harikasınız."))
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN","Çok yerde kahve içtim ama bu kadar lezzetlisine denk gelmedim. Üstelik paket servis veriyorlar. Sanırım artık buranın bağımlısı olduk. Tebrik ederim harikasınız."))
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN","Çok yerde kahve içtim ama bu kadar lezzetlisine denk gelmedim. Üstelik paket servis veriyorlar. Sanırım artık buranın bağımlısı olduk. Tebrik ederim harikasınız."))
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN","Çok yerde kahve içtim ama bu kadar lezzetlisine denk gelmedim. Üstelik paket servis veriyorlar. Sanırım artık buranın bağımlısı olduk. Tebrik ederim harikasınız."))
        YorumList.add(Yorum(R.drawable.elon_musk,simdikiTarihSaat.toString(),"Emre KAHRAMAN",urun_yorum.toString()))


        yorumlarAdapter = YorumlarAdapter(YorumList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = yorumlarAdapter







    }
}