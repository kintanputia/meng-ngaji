package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.helper.Masjid
import com.example.meng_ngaji.helper.PengajianAdapter
import com.example.meng_ngaji.helper.RetrofitClient
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian.*
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian.mRecyclerView
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*
import kotlinx.android.synthetic.main.list_masjid.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilCariPengajianActivity : AppCompatActivity() {
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
        RetrofitClient.instance.getPosts().enqueue(object : Callback<ArrayList<Masjid>> {
            override fun onResponse(
                call: Call<ArrayList<Masjid>>,
                response: Response<ArrayList<Masjid>>
            ) {
                val responseCode = response.code().toString()
                val adapter = PengajianAdapter(daftar, object : PengajianAdapter.OnItemClickCallback{
                    override fun onItemClick(data: Masjid) {
                        val intent = Intent(this@HasilCariPengajianActivity, DetailPengajianMasjidActivity::class.java)
                        intent.putExtra(DetailPengajianMasjidActivity.EXTRA_NAME, data.namaMasjid)
                        startActivity(intent)
                    }
                })
                lblMasjid.text = responseCode
                response.body()?.let{ daftar.addAll(it)}
                adapter.notifyDataSetChanged()
                mRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<Masjid>>, t: Throwable) {

            }

        })

    }
//
//    private fun setRecyclerView (){
//        mRecyclerView.setHasFixedSize(true)
//        mRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        for (i in 0 until listMasjid.size){
//
//            daftar.add(
//                Masjid(
//                    listMasjid.get(i),
//                    listJudul.get(i),
//                    listWaktu.get(i),
//                    listJarak.get(i)
//                )
//            )
//
//            if(listMasjid.size - 1 == i){
//                // init adapter yang telah dibuat tadi
////                val adapter = PengajianAdapter(this,daftar)
//                val adapter = PengajianAdapter(this, daftar, object : PengajianAdapter.OnItemClickCallback{
//                    override fun onItemClick(data: Masjid) {
//                        val intent = Intent(this@HasilCariPengajianActivity, DetailPengajianMasjidActivity::class.java)
//                        intent.putExtra(DetailPengajianMasjidActivity.EXTRA_NAME, data.namaMasjid)
//                        startActivity(intent)
//                    }
//                })
//                adapter.notifyDataSetChanged()
//
//                //tampilkan data dalam recycler view
//                mRecyclerView.adapter = adapter
//            }
//
//        }
//    }

}