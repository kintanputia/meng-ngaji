package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.helper.Masjid
import com.example.meng_ngaji.helper.PengajianAdapter
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*

class HasilCariPengajianTerdekatActivity : AppCompatActivity() {
    val daftar = ArrayList<Masjid>()
    val listMasjid = arrayOf(
        "Muslimin",
        "Nurul Ikhlas"
    )
    val listJudul = arrayOf(
        "Meraih Surga Allah",
        "Mencintai Rasulullah"
    )
    val listWaktu = arrayOf(
        "2021/11/10 - 09.00",
        "2021/11/12 - 19.00"
    )
    val listJarak = arrayOf(
        "0,5 km",
        "1 km"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_cari_pengajian_terdekat)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        for (i in 0 until listMasjid.size){

            daftar.add(
                Masjid(
                    listMasjid.get(i),
                    listJudul.get(i),
                    listWaktu.get(i),
                    listJarak.get(i)
                )
            )

            if(listMasjid.size - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = PengajianAdapter(this,daftar)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                mRecyclerView.adapter = adapter
            }

        }
    }
}