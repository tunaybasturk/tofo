package com.example.tofo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tofo.databinding.ListelemeRowBinding
import com.example.tofo.databinding.YorumlarRowBinding

class YorumlarAdapter(var yorumlarList : List<Yorum>) : RecyclerView.Adapter<YorumlarAdapter.ViewHolder>(){






    class ViewHolder(val binding: YorumlarRowBinding) : RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =YorumlarRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = yorumlarList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.kisiYorum.setText(yorumlarList[position].kullanici_yorum)
        holder.binding.tarih.setText(yorumlarList[position].tarih)
        holder.binding.kullaniciIsim.setText(yorumlarList[position].kullanici_isim)
        holder.binding.kullaniciResim.setImageResource(yorumlarList[position].kullanici_resim)

    }



}





