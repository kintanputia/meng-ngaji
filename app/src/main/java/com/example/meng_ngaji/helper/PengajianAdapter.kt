package com.example.meng_ngaji.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meng_ngaji.R
import kotlinx.android.synthetic.main.list_masjid.view.*
import retrofit2.Callback

class PengajianAdapter(private val arrayList: ArrayList<Masjid>, private var onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<PengajianAdapter.PostViewHolder>(){

//    private lateinit var onItemClickCallback: OnItemClickCallback
    inner class PostViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        fun bind(postResponse : Masjid) {
            with(itemView){
                val text = "title : ${postResponse.namaMasjid}"
                lblMasjid.text = text
            }
        }
    }

    fun setOnItemClickCallback(
        onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_masjid,parent,false)
        return PostViewHolder(view)
//        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_masjid,parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

//        holder.view.lblMasjid.text = arrayList?.get(position)?.namaMasjid
//        holder.view.lblJudul.text = arrayList?.get(position)?.judul
//        holder.view.lblWaktu.text = arrayList?.get(position)?.waktu
//        holder.view.lblJarak.text = arrayList?.get(position)?.jarak
        holder.bind(arrayList[position])
        holder.itemView.setOnClickListener() {
            onItemClickCallback
                .onItemClick(arrayList[holder.adapterPosition])
        }
    }
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickCallback {
        fun onItemClick(data: Masjid)
    }
}