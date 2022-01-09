package com.example.meng_ngaji.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.R
import com.example.meng_ngaji.data_class.Terjadwal
import kotlinx.android.synthetic.main.list_pengajian2.view.*

class TerjadwalAdapter(private val list: ArrayList<Terjadwal>) : RecyclerView.Adapter<TerjadwalAdapter.PostViewHolder>(){
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: Terjadwal){
            with(itemView){
                val waktuPengajian = "${postResponse.waktu_pengajian}\n"
                val tglPengajian = "${postResponse.tgl_pengajian}\n"
                val judulPengajian = "${postResponse.judul_pengajian}\n"
                lblJudul.text = judulPengajian
                lblJam.text = waktuPengajian
                lblTanggal.text = tglPengajian
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_pengajian2, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }
}