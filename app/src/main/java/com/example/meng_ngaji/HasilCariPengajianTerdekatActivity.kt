package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianTerdekatAdapter
import com.example.meng_ngaji.helper.PostResponse
import com.example.meng_ngaji.helper.RetrofitClient
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilCariPengajianTerdekatActivity : AppCompatActivity() {
    val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_cari_pengajian_terdekat)

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
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}