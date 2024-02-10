package com.example.tofo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tofo.databinding.BildirimlerRowBinding

class BildirimlerAdapter(var bildirimlerlist : List<Bildirim>) : RecyclerView.Adapter<BildirimlerAdapter.BildirimlerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BildirimlerHolder {
      val recyclerRowBinding : BildirimlerRowBinding = BildirimlerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BildirimlerHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: BildirimlerHolder, position: Int) {
        holder.recyclerRowBinding.bildirimMetni.setText(bildirimlerlist[position].bildirimtext)
        holder.recyclerRowBinding.gitButon.setText(bildirimlerlist[position].butontext)
        holder.recyclerRowBinding.bildirimRowIcon.setImageResource(bildirimlerlist[position].image)
    }


    override fun getItemCount(): Int {
        return bildirimlerlist.size
    }
    class BildirimlerHolder(val recyclerRowBinding: BildirimlerRowBinding): RecyclerView.ViewHolder(recyclerRowBinding.root){

    }
}