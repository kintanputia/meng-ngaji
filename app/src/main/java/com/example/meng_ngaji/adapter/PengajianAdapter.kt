package com.example.meng_ngaji.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.R
import com.example.meng_ngaji.data_class.Masjid
import kotlinx.android.synthetic.main.list_masjid.view.*

class PengajianAdapter(private val list: ArrayList<Masjid>): RecyclerView.Adapter<PengajianAdapter.PostViewHolder>(){
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: Masjid){
            with(itemView){
                val namaMasjid = "Masjid ${postResponse.nama_masjid}\n"
                val waktuPengajian = "${postResponse.waktu_pengajian}\n"
                val tglPengajian = "${postResponse.tgl_pengajian}\n"
                val judulPengajian = "${postResponse.judul_pengajian}\n"
                lblMasjid.text = namaMasjid
                lblJudul.text = judulPengajian
                lblWaktu.text = waktuPengajian
                lblTgl.text = tglPengajian
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(
        onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_masjid, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener() {
            onItemClickCallback.onItemClick(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClick(data: Masjid)
    }
}