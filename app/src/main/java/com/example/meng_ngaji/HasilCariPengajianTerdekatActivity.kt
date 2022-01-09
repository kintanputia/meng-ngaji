package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianTerdekatAdapter
import com.example.meng_ngaji.data_class.PostResponse
import com.example.meng_ngaji.helper.RetrofitClient
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilCariPengajianTerdekatActivity : AppCompatActivity() {
    val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_cari_pengajian_terdekat)

        // set toolbar as support action bar
        setSupportActionBar(toolbar3)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            title = "Masjid Terdekat"

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                response.body()?.let{list.addAll(it)}
                val adapter  = PengajianTerdekatAdapter(list)
                mRecyclerView.adapter = adapter

                //intent recycler view
                adapter.setOnItemClickCallback(object : PengajianTerdekatAdapter.OnItemClickCallback{
                    override fun onItemClick(data: PostResponse) {
                        val manageDetailIntent = Intent(this@HasilCariPengajianTerdekatActivity, DetailPengajianMasjidActivity::class.java)
                            .apply {
                                putExtra(DetailPengajianMasjidActivity.EXTRA_NAME, data.nama_masjid)
                                putExtra(DetailPengajianMasjidActivity.EXTRA_ADRESS, data.alamat_masjid)
                                putExtra(DetailPengajianMasjidActivity.EXTRA_ID, data.id_masjid)
                            }
                        startActivity(manageDetailIntent)
                    }
                })
            }
            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}