package com.example.meng_ngaji.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.PengajianFragment
import com.example.meng_ngaji.R
import kotlinx.android.synthetic.main.list_masjid.view.lblJudul
import kotlinx.android.synthetic.main.list_pengajian2.view.*

class TerjadwalAdapter(private val context: PengajianFragment, private val arrayList: ArrayList<Terjadwal>) : RecyclerView.Adapter<TerjadwalAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_pengajian2,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lblJudul.text = arrayList?.get(position)?.judul
        holder.view.lblTanggal.text = arrayList?.get(position)?.tanggal
        holder.view.lblJam.text = arrayList?.get(position)?.jam
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}