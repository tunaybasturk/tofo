package com.example.tofo

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.media.effect.Effect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tofo.databinding.ActivityUrunGosterimBinding

class Urun_Gosterim : AppCompatActivity() {

    lateinit var binding : ActivityUrunGosterimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrunGosterimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gelenIntent = intent
        val urun_isim = gelenIntent.getStringExtra("ürün_isim")
        val urun_fiyat= gelenIntent.getStringExtra("ürün_fiyat")
        val urun_resim = gelenIntent.getIntExtra("ürün_resim",0)
        val urun_yildiz = gelenIntent.getIntExtra("ürün_yildiz",0)
        val urun_yorum = gelenIntent.getIntExtra("ürün_yorum",0)


 /*  Denediğim resim boyutlandırma kodları

        val urunResimBitmap = BitmapFactory.decodeResource(resources, urun_resim)

        val hedefGenislik = 500 // HD genişlik
        val hedefYukseklik = 500 // HD yükseklik


        val orijinalGenislik = urunResimBitmap.width
        val orijinalYukseklik = urunResimBitmap.height


        val genislikOlcek = orijinalGenislik.toFloat() / hedefGenislik.toFloat()
        val yukseklikOlcek = orijinalYukseklik.toFloat() / hedefYukseklik.toFloat()


        val boyutlandirilmisBitmap = Bitmap.createScaledBitmap(
            urunResimBitmap,
            (orijinalGenislik / genislikOlcek).toInt(),
            (orijinalYukseklik / yukseklikOlcek).toInt(),
            true
        )

*/
        binding.urunYorumu.setOnClickListener {

            var dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_yorum)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val window = dialog.window
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            val paylas_buton = dialog.findViewById<Button>(R.id.paylas_btn)

            val carpi_icon = dialog.findViewById<ImageView>(R.id.cancel_icon)

            val yorum_yap = dialog.findViewById<EditText>(R.id.yorum_yaz)

            paylas_buton.setOnClickListener {

                val yorumal = yorum_yap.text.toString().trim()

                if(yorumal.isNotEmpty()){
                    intent = Intent(this,Yorumlar::class.java)
                    intent.putExtra("ürün_isim",urun_isim)
                    intent.putExtra("ürün_yildiz",urun_yildiz)
                    intent.putExtra("ürün_fiyat",urun_fiyat)
                    intent.putExtra("ürün_yorum",yorumal)
                    intent.putExtra("ürün_resim",urun_resim)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this,"Boş şekilde yorum atamazsınız",Toast.LENGTH_SHORT).show()
                }

            }

            carpi_icon.setOnClickListener {
                dialog.dismiss()
            }


            dialog.show()
        }



        binding.urunYildizi.setOnClickListener {

            var dialog = Dialog(this)


            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_yildiz)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val yildiz1 = dialog.findViewById<ImageView>(R.id.yildiz1)
            val yildiz2 = dialog.findViewById<ImageView>(R.id.yildiz2)
            val yildiz3 = dialog.findViewById<ImageView>(R.id.yildiz3)
            val yildiz4 = dialog.findViewById<ImageView>(R.id.yildiz4)
            val yildiz5 = dialog.findViewById<ImageView>(R.id.yildiz5)
            val urun_diyalog_isim = dialog.findViewById<TextView>(R.id.urun_isim)
            val paylas_buton = dialog.findViewById<Button>(R.id.paylas_btn)

            val carpi_icon = dialog.findViewById<ImageView>(R.id.cancel_icon)

            val window = dialog.window
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)


            urun_diyalog_isim.setText(urun_isim)



            yildiz1.setOnClickListener{
                yildiz1.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz2.setImageResource(R.drawable.empty_star_icon)
                yildiz3.setImageResource(R.drawable.empty_star_icon)
                yildiz4.setImageResource(R.drawable.empty_star_icon)
                yildiz5.setImageResource(R.drawable.empty_star_icon)
                Toast.makeText(this,"Bir yıldız oy verildi",Toast.LENGTH_SHORT).show()

            }

            yildiz2.setOnClickListener {
                yildiz1.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz2.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz3.setImageResource(R.drawable.empty_star_icon)
                yildiz4.setImageResource(R.drawable.empty_star_icon)
                yildiz5.setImageResource(R.drawable.empty_star_icon)
                Toast.makeText(this,"İki yıldız oy verildi",Toast.LENGTH_SHORT).show()
            }

            yildiz3.setOnClickListener {
                yildiz1.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz2.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz3.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz4.setImageResource(R.drawable.empty_star_icon)
                yildiz5.setImageResource(R.drawable.empty_star_icon)
                Toast.makeText(this,"Üç yıldız oy verildi",Toast.LENGTH_SHORT).show()

            }

            yildiz4.setOnClickListener {

                yildiz1.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz2.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz3.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz4.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz5.setImageResource(R.drawable.empty_star_icon)
                Toast.makeText(this,"Dört yıldız oy verildi",Toast.LENGTH_SHORT).show()
            }

            yildiz5.setOnClickListener {
                yildiz1.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz2.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz3.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz4.setImageResource(R.drawable.listeleme_urun_yildiz)
                yildiz5.setImageResource(R.drawable.listeleme_urun_yildiz)
                Toast.makeText(this,"Beş yıldız oy verildi",Toast.LENGTH_SHORT).show()
            }




            paylas_buton.setOnClickListener {
                dialog.dismiss()
            }

            carpi_icon.setOnClickListener {
                dialog.dismiss()
            }


            dialog.show()


        }


        binding.urunIsmi.setText(urun_isim)
        binding.urunFiyat.setText(urun_fiyat)
        binding.urunResim.setImageResource(urun_resim)
       // binding.urunResim.setImageBitmap(boyutlandirilmisBitmap)

        if(urun_yildiz == 1){
            binding.yildiz2.visibility = View.GONE
            binding.yildiz3.visibility = View.GONE
            binding.yildiz4.visibility = View.GONE
            binding.yildiz5.visibility = View.GONE

        }
        else if(urun_yildiz == 2){
            binding.yildiz3.visibility = View.GONE
            binding.yildiz4.visibility = View.GONE
            binding.yildiz5.visibility = View.GONE
        }
        else if(urun_yildiz == 3){
            binding.yildiz4.visibility = View.GONE
            binding.yildiz5.visibility = View.GONE
        }
        else if(urun_yildiz == 4){
            binding.yildiz5.visibility = View.GONE
        }

        binding.urunYorum.setText("$urun_yorum Yorum Yapıldı")

        binding.urunGosterimGeriIcon.setOnClickListener{
            val intent = Intent(this,Listeleme::class.java)
            startActivity(intent)
        }

        binding.urunGosterimBildirimler.setOnClickListener {
            val intent = Intent(this,Bildirimler::class.java)
            startActivity(intent)
        }

    }








}