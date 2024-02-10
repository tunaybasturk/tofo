package com.example.tofo

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tofo.databinding.ActivityFiltrelerBinding

class Filtreler : AppCompatActivity() {

    private lateinit var binding : ActivityFiltrelerBinding
    val mesafecirclecolor = R.color.mesafe_circle_color
    val beyazrenk = R.color.white
    var listelemeisaretlemekontrol = ArrayList<Int>()
    val listelemeboyacolor = R.color.listeleme_circle_color
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltrelerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listelemeisaretlemekontrol = ArrayList()
        listelemeisaretlemekontrol.add(0)
        listelemeisaretlemekontrol.add(0)
        listelemeisaretlemekontrol.add(0)
        listelemeisaretlemekontrol.add(0)


        binding.filtrelerGeriIcon.setOnClickListener {
            intent = Intent(this,Anasayfa::class.java)
            startActivity(intent)
        }

        binding.filtrelerBildirimler.setOnClickListener {
            intent = Intent(this,Bildirimler::class.java)
            startActivity(intent)
        }


        binding.enYakinListelemeCircle.setOnClickListener {
                if(listelemeisaretlemekontrol[0] == 0){
                    binding.enYakinListelemeCircle.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
                    listelemeisaretlemekontrol.set(0,1)
                }
                else if(listelemeisaretlemekontrol[0] == 1){
                    binding.enYakinListelemeCircle.setColorFilter(ContextCompat.getColor(this,listelemeboyacolor))
                    listelemeisaretlemekontrol.set(0,0)
                }
        }

        binding.indirimEnCokListelemeCircle.setOnClickListener {
            if(listelemeisaretlemekontrol[1] == 0){
                binding.indirimEnCokListelemeCircle.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
                listelemeisaretlemekontrol.set(1,1)
            }
            else if(listelemeisaretlemekontrol[1] == 1){
                binding.indirimEnCokListelemeCircle.setColorFilter(ContextCompat.getColor(this,listelemeboyacolor))
                listelemeisaretlemekontrol.set(1,0)
            }
        }

        binding.puanEnYuksekListelemeCircle.setOnClickListener {
            if(listelemeisaretlemekontrol[2] == 0){
                binding.puanEnYuksekListelemeCircle.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
                listelemeisaretlemekontrol.set(2,1)
            }
            else if(listelemeisaretlemekontrol[2] == 1){
                binding.puanEnYuksekListelemeCircle.setColorFilter(ContextCompat.getColor(this,listelemeboyacolor))
                listelemeisaretlemekontrol.set(2,0)
            }

        }

        binding.ziyaretEnCokListelemeCircle.setOnClickListener {
            if(listelemeisaretlemekontrol[3] == 0){
                binding.ziyaretEnCokListelemeCircle.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
                listelemeisaretlemekontrol.set(3,1)
            }
            else if(listelemeisaretlemekontrol[3] == 1){
                binding.ziyaretEnCokListelemeCircle.setColorFilter(ContextCompat.getColor(this,listelemeboyacolor))
                listelemeisaretlemekontrol.set(3,0)
            }
        }




        binding.mesafeCircle1.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_bos_boya()
            line3_circle3_bos_boya()
            line4_circle4_bos_boya()
            line5_circle5_bos_boya()
            line6_circle6_bos_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle2.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_bos_boya()
            line4_circle4_bos_boya()
            line5_circle5_bos_boya()
            line6_circle6_bos_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle3.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_bos_boya()
            line5_circle5_bos_boya()
            line6_circle6_bos_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle4.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_bos_boya()
            line6_circle6_bos_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle5.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_bos_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle6.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_dolu_boya()
            line7_circle7_bos_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle7.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_dolu_boya()
            line7_circle7_dolu_boya()
            line8_circle8_bos_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle8.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_dolu_boya()
            line7_circle7_dolu_boya()
            line8_circle8_dolu_boya()
            line9_circle9_bos_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle9.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_dolu_boya()
            line7_circle7_dolu_boya()
            line8_circle8_dolu_boya()
            line9_circle9_dolu_boya()
            line10_circle10_bos_boya()
        }
        binding.mesafeCircle10.setOnClickListener {
            line1_circle1_dolu_boya()
            line2_circle2_dolu_boya()
            line3_circle3_dolu_boya()
            line4_circle4_dolu_boya()
            line5_circle5_dolu_boya()
            line6_circle6_dolu_boya()
            line7_circle7_dolu_boya()
            line8_circle8_dolu_boya()
            line9_circle9_dolu_boya()
            line10_circle10_dolu_boya()
        }
    }



    private fun line1_circle1_dolu_boya(){
        binding.line1.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle1.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line2_circle2_dolu_boya(){
        binding.line2.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle2.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line3_circle3_dolu_boya(){
        binding.line3.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle3.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line4_circle4_dolu_boya(){
        binding.line4.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle4.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line5_circle5_dolu_boya(){
        binding.line5.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle5.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line6_circle6_dolu_boya(){
        binding.line6.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle6.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line7_circle7_dolu_boya(){
        binding.line7.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle7.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line8_circle8_dolu_boya(){
        binding.line8.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle8.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line9_circle9_dolu_boya(){
        binding.line9.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle9.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
    }
    private fun line10_circle10_dolu_boya(){
        binding.line10.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
        binding.mesafeCircle10.setColorFilter(ContextCompat.getColor(this,mesafecirclecolor))
        binding.line11.setBackgroundColor(ContextCompat.getColor(this,mesafecirclecolor))
    }


    private fun line1_circle1_bos_boya(){
        binding.line1.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle1.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line2_circle2_bos_boya(){
        binding.line2.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle2.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line3_circle3_bos_boya(){
        binding.line3.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle3.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line4_circle4_bos_boya(){
        binding.line4.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle4.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line5_circle5_bos_boya(){
        binding.line5.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle5.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line6_circle6_bos_boya(){
        binding.line6.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle6.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line7_circle7_bos_boya(){
        binding.line7.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle7.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line8_circle8_bos_boya(){
        binding.line8.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle8.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line9_circle9_bos_boya(){
        binding.line9.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle9.setColorFilter(ContextCompat.getColor(this,beyazrenk))
    }
    private fun line10_circle10_bos_boya(){
        binding.line10.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
        binding.mesafeCircle10.setColorFilter(ContextCompat.getColor(this,beyazrenk))
        binding.line11.setBackgroundColor(ContextCompat.getColor(this,beyazrenk))
    }



}