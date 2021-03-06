package com.example.meng_ngaji.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.R
import com.example.meng_ngaji.data_class.PengajianMasjid
import kotlinx.android.synthetic.main.list_pengajian.view.*

class PengajianMasjidAdapter(private val list: ArrayList<PengajianMasjid>): RecyclerView.Adapter<PengajianMasjidAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: PengajianMasjid){
            with(itemView){
                val judulPengajian = "${postResponse.judul_pengajian}\n"
                tvJudul.text = judulPengajian
            }
        }
    }

    private lateinit var onItemClickCallback: PengajianMasjidAdapter.OnItemClickCallback

    fun setOnItemClickCallback(
        onItemClickCallback: PengajianMasjidAdapter.OnItemClickCallback
    ) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_pengajian, parent, false)
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
        fun onItemClick(data: PengajianMasjid)
    }
}