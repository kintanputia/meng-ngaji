package com.example.meng_ngaji

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meng_ngaji.adapter.PengajianTerdekatAdapter
import com.example.meng_ngaji.adapter.TerjadwalAdapter
import com.example.meng_ngaji.data_class.Terjadwal
import com.example.meng_ngaji.helper.RetrofitClient
import com.example.meng_ngaji.helper.SharedPref
import kotlinx.android.synthetic.main.activity_hasil_cari_pengajian_terdekat.*
import kotlinx.android.synthetic.main.fragment_pengajian.*
import kotlinx.android.synthetic.main.fragment_pengajian.mRecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PengajianFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PengajianFragment : Fragment(){

    val list = ArrayList<Terjadwal>()
    private lateinit var s: SharedPref

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengajian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_terdekat.setOnClickListener(){
            startActivity(Intent(context, HasilCariPengajianTerdekatActivity::class.java))
        }
        search.setOnClickListener(){
            searchKajian()
        }

        //Recycler
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        s = SharedPref(requireActivity())
        val user = s.getUser()!!

        RetrofitClient.instance.getPf(user.id).enqueue(object: Callback<ArrayList<Terjadwal>>{
            override fun onResponse(
                call: Call<ArrayList<Terjadwal>>,
                response: Response<ArrayList<Terjadwal>>
            ) {
                response.body()?.let{list.addAll(it)}
                val adapter  = TerjadwalAdapter(list)
                mRecyclerView.adapter = adapter
            }
            override fun onFailure(call: Call<ArrayList<Terjadwal>>, t: Throwable) {

            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PengajianFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PengajianFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun searchKajian(){
        val intent = Intent(context, HasilCariPengajianActivity::class.java)
        intent.putExtra("nama_masjid", cariMasjid.text.toString())
        startActivity(intent)
    }
}