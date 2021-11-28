package com.example.meng_ngaji.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.R
import kotlinx.android.synthetic.main.list_masjid.view.*

class PengajianAdapter(private val context: Context, private val arrayList: ArrayList<Masjid>) : RecyclerView.Adapter<PengajianAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_masjid,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lblMasjid.text = arrayList?.get(position)?.namaMasjid
        holder.view.lblJudul.text = arrayList?.get(position)?.judul
        holder.view.lblWaktu.text = arrayList?.get(position)?.waktu
        holder.view.lblJarak.text = arrayList?.get(position)?.jarak
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}