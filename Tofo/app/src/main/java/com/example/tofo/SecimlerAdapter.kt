package com.example.tofo

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SecimlerAdapter(private val secimActivity: Secim): RecyclerView.Adapter<SecimlerAdapter.ViewHolder>() {


    private var secimlerList: ArrayList<String> = ArrayList()
    val secildirenk = R.drawable.secildi_border
    val secilmedirenk = R.drawable.secimler_border
    var secimlersecildiList :ArrayList<Int> = ArrayList()
    var secilenlerList : ArrayList<String> = ArrayList()
    var secimDurumlari : ArrayList<Boolean> = ArrayList()


    fun size_esitleme(){
        for(i in 0..itemCount-1){
            secimlersecildiList.add(0)
        }
        for (i in 0..itemCount-1){
            secimDurumlari.add(false)
        }
        for (i in 0..itemCount-1){
            secilenlerList.add("")
        }
    }

    fun guncelle_secım_list(secimler_List: ArrayList<String>) {
        this.secimlerList = secimler_List

        notifyDataSetChanged()
    }

    fun secilenleri_getir(): ArrayList<String> {
        notifyDataSetChanged()
        return secilenlerList
    }

    fun filtrelenmisliste(filtrelenmis_List :ArrayList<String>){
            this.secimlerList = filtrelenmis_List
            notifyDataSetChanged()

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textView = itemView.findViewById<TextView>(R.id.secim_metini)


        init {


            itemView.setOnClickListener{
                Log.d("MARS",adapterPosition.toString())

                if(secimlersecildiList.get(adapterPosition) == 0){
                    secimlersecildiList.set(adapterPosition,1)
                    textView.setBackgroundResource(secildirenk)
                    val secilenurun= textView.text.toString()
                    secimDurumlari.set(adapterPosition,true)
                    secimActivity.updateSecimBitirTextView(secimDurumlari)
                    secilenlerList.set(adapterPosition,secilenurun)
                }
                else {
                    secimlersecildiList.set(adapterPosition,0)
                    textView.setBackgroundResource(secilmedirenk)
                    val secilenurun= textView.text.toString()
                    secimDurumlari.set(adapterPosition,false)
                   secimActivity.updateSecimBitirTextView(secimDurumlari)
                    secilenlerList.set(adapterPosition,"")
                }


            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.secimler_row,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = secimlerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){





        holder.textView.setBackgroundResource(secilmedirenk)
        holder.textView.text =secimlerList[position]






    }


}