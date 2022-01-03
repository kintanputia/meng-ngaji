package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianMasjidAdapter
import com.example.meng_ngaji.helper.PengajianMasjid
import com.example.meng_ngaji.helper.RetrofitClient
import kotlinx.android.synthetic.main.activity_detail_pengajian_masjid.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPengajianMasjidActivity : AppCompatActivity() {

    val list = ArrayList<PengajianMasjid>()

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ADRESS = "extra_adress"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengajian_masjid)

        nama_masjid.setText("Masjid " + intent.getStringExtra(EXTRA_NAME))
        alamatMasjid.setText(intent.getStringExtra(EXTRA_ADRESS))
        val idMasjid = intent.getIntExtra(EXTRA_ID, 0)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.daftarPengajian(idMasjid).enqueue(object: Callback<ArrayList<PengajianMasjid>>{
            override fun onResponse(
                call: Call<ArrayList<PengajianMasjid>>,
                response: Response<ArrayList<PengajianMasjid>>
            ) {
                response.body()?.let { list.addAll(it)}
                val adapter = PengajianMasjidAdapter(list)
                mRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PengajianMasjid>>, t: Throwable) {

            }

        })
    }
}