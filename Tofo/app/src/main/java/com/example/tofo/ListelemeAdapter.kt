package com.example.tofo

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tofo.databinding.ListelemeRowBinding


class ListelemeAdapter(var listelemeList : List<Urun>,context:Context): RecyclerView.Adapter<ListelemeAdapter.ViewHolder>() {



    fun filtrelenmisliste(filtrelenmis_List :ArrayList<Urun>){
        this.listelemeList = filtrelenmis_List
        notifyDataSetChanged()

    }


    class ViewHolder(val binding: ListelemeRowBinding) : RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ListelemeRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listelemeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val resID = listelemeList[position].urun_resim
        val context = holder.itemView.context
        val originalBitmap = BitmapFactory.decodeResource(context.resources, resID)
        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 75, 95, false)

        val gonderilecekscaledBitMap = Bitmap.createScaledBitmap(originalBitmap, 75, 95, false)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,Urun_Gosterim::class.java)
            intent.putExtra("ürün_isim",listelemeList.get(position).urun_isim)
            intent.putExtra("ürün_fiyat",listelemeList.get(position).urun_fiyat)
            intent.putExtra("ürün_resim",listelemeList.get(position).urun_resim)
            intent.putExtra("ürün_yildiz",listelemeList.get(position).urun_yildiz)
            intent.putExtra("ürün_yorum",listelemeList.get(position).yorum_sayisi)
            holder.itemView.context.startActivity(intent)
        }


        holder.binding.urunResim.setImageBitmap(scaledBitmap)
        holder.binding.urunIsmi.setText(listelemeList.get(position).urun_isim)
        holder.binding.urunKategori.setImageResource(listelemeList.get(position).urun_kategori_resim)
        holder.binding.urunYorumSayisi.setText("${listelemeList.get(position).yorum_sayisi} yorum yapıldı" )
        holder.binding.urunUzaklik.setText("${listelemeList.get(position).urun_uzaklik} uzaklıkta")
        holder.binding.urunFiyati.setText(listelemeList.get(position).urun_fiyat)
        if(listelemeList.get(position).urun_yildiz == 1){
            holder.binding.yildiz2.visibility = View.GONE
            holder.binding.yildiz3.visibility = View.GONE
            holder.binding.yildiz4.visibility = View.GONE
            holder.binding.yildiz5.visibility = View.GONE

        }
        else if(listelemeList.get(position).urun_yildiz == 2){
            holder.binding.yildiz3.visibility = View.GONE
            holder.binding.yildiz4.visibility = View.GONE
            holder.binding.yildiz5.visibility = View.GONE
        }
        else if(listelemeList.get(position).urun_yildiz == 3){
            holder.binding.yildiz4.visibility = View.GONE
            holder.binding.yildiz5.visibility = View.GONE
        }
        else if(listelemeList.get(position).urun_yildiz == 4){
            holder.binding.yildiz5.visibility = View.GONE
        }

    }
}