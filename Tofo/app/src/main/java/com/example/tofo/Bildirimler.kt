package com.example.tofo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tofo.databinding.ActivityBildirimlerBinding

class Bildirimler : AppCompatActivity() {

    private lateinit var binding : ActivityBildirimlerBinding
    private lateinit var bildirimler : ArrayList<Bildirim>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBildirimlerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bildirimler = ArrayList()
        val DenemeBildirim  = Bildirim(R.drawable.anasayfa_bildirim_icon,"Bu bir örnek bildirim", "Listeye Ekle")
        bildirimler.add(DenemeBildirim)
        bildirimler.add(DenemeBildirim)
        bildirimler.add(DenemeBildirim)
        bildirimler.add(DenemeBildirim)
        bildirimler.add(DenemeBildirim)
        bildirimler.add(DenemeBildirim)
        bildirim_gonder(bildirimler)


        binding.bildirimlerGeriIcon.setOnClickListener {
            intent = Intent(this,Anasayfa::class.java)
            startActivity(intent)
        }

        binding.kullaniciIcon.setOnClickListener {
            intent = Intent(this,Kullanici_Profili::class.java)
            startActivity(intent)
        }

    }


    private fun bildirim_gonder(bildirimList: List<Bildirim>) {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        val bildirimAdapter = BildirimlerAdapter(bildirimList)
        binding.recyclerViewList.adapter = bildirimAdapter
    }

}