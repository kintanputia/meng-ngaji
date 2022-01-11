package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianMasjidAdapter
import com.example.meng_ngaji.data_class.PengajianMasjid
import com.example.meng_ngaji.data_class.Terjadwal
import com.example.meng_ngaji.helper.RetrofitClient
import com.example.meng_ngaji.helper.SharedPref
import kotlinx.android.synthetic.main.activity_detail_pengajian_masjid.*
import kotlinx.android.synthetic.main.dialog_view.*
import kotlinx.android.synthetic.main.dialog_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPengajianMasjidActivity : AppCompatActivity() {

    val list = ArrayList<PengajianMasjid>()
    private lateinit var s: SharedPref

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ADRESS = "extra_adress"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengajian_masjid)

        nama_masjid.text = "Masjid " + intent.getStringExtra(EXTRA_NAME)
        alamatMasjid.text = intent.getStringExtra(EXTRA_ADRESS)
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

                adapter.setOnItemClickCallback(object : PengajianMasjidAdapter.OnItemClickCallback{
                    override fun onItemClick(data: PengajianMasjid) {
                        s = SharedPref(this@DetailPengajianMasjidActivity)
                        val user = s.getUser()!!
                        val view = LayoutInflater.from(this@DetailPengajianMasjidActivity).inflate(R.layout.dialog_view, null)
                        val builder = AlertDialog.Builder(this@DetailPengajianMasjidActivity)
                        builder.setView(view)

                        val dialog = builder.create()
                        dialog.show()
                        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                        view.tvJudulKajian.text = data.judul_pengajian
                        view.tvJam.text = data.waktu_pengajian
                        view.tvTgl.text = data.tgl_pengajian.toString()
                        view.tvPengisi.text = data.pengisi_kajian

                        view.btnReminder.setOnClickListener{

                            RetrofitClient.instance.addPf(data.id_pengajian, user.id).enqueue(object: Callback<ArrayList<Terjadwal>>{
                                override fun onResponse(
                                    call: Call<ArrayList<Terjadwal>>,
                                    response: Response<ArrayList<Terjadwal>>
                                ) {
                                    val text = "Pengajian berhasil ditambahkan ke daftar favorit"
                                    val duration = Toast.LENGTH_SHORT

                                    val toast = Toast.makeText(applicationContext, text, duration)
                                    toast.show()
                                    dialog.dismiss()
                                }

                                override fun onFailure(
                                    call: Call<ArrayList<Terjadwal>>,
                                    t: Throwable
                                ) {
                                    val text = "Error"
                                    val duration = Toast.LENGTH_SHORT

                                    val toast = Toast.makeText(applicationContext, text, duration)
                                    toast.show()
                                }

                            })
                        }
                    }
                })
            }

            override fun onFailure(call: Call<ArrayList<PengajianMasjid>>, t: Throwable) {

            }

        })
    }
}