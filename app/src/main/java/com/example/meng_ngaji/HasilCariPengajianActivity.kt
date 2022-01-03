package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianAdapter
import com.example.meng_ngaji.helper.Masjid
import com.example.meng_ngaji.helper.RetrofitClient
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian.*
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian.mRecyclerView
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian.toolbar3
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilCariPengajianActivity : AppCompatActivity() {
    val list = ArrayList<Masjid>()

    companion object {
        const val NAMA_MASJID = "nama_masjid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_cari_pengajian)

        // set toolbar as support action bar
        setSupportActionBar(toolbar3)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            title = intent.getStringExtra(NAMA_MASJID)

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val keyword = intent.getStringExtra(NAMA_MASJID)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.searchPengajian(keyword).enqueue(object: Callback<ArrayList<Masjid>>{
            override fun onResponse(
                call: Call<ArrayList<Masjid>>,
                response: Response<ArrayList<Masjid>>
            ) {
                response.body()?.let { list.addAll(it)}
                val adapter = PengajianAdapter(list)
                mRecyclerView.adapter = adapter

                //intent recycler view
                adapter.setOnItemClickCallback(object : PengajianAdapter.OnItemClickCallback{
                    override fun onItemClick(data: Masjid) {
                        val manageDetailIntent = Intent(this@HasilCariPengajianActivity, DetailPengajianMasjidActivity::class.java)
                            .apply {
                                putExtra(DetailPengajianMasjidActivity.EXTRA_NAME, data.nama_masjid)
                            }
                        startActivity(manageDetailIntent)
                    }
                })
            }

            override fun onFailure(call: Call<ArrayList<Masjid>>, t: Throwable) {

            }

        })
    }

}