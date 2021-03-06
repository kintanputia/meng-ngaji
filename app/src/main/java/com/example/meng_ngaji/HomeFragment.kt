package com.example.meng_ngaji

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.example.meng_ngaji.data_class.PengajianTerdekat
import com.example.meng_ngaji.data_class.Terjadwal
import com.example.meng_ngaji.helper.RetrofitClient
import com.example.meng_ngaji.helper.SharedPref
import kotlinx.android.synthetic.main.activity_detail_pengajian_masjid.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_prayer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var s: SharedPref
    val data = ArrayList<PengajianTerdekat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val waktsolat = view.findViewById<TextView>(R.id.waktusolat)
        waktsolat.setOnClickListener {
            val prayerFragment = PrayerFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, prayerFragment)
            transaction.commit()
        }
        val cardprayer = view.findViewById<CardView>(R.id.cardprayer)
        cardprayer.setOnClickListener {
            val prayerFragment = PrayerFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, prayerFragment)
            transaction.commit()
        }
        val jadwal = view.findViewById<TextView>(R.id.jdwlpngajian)
        jadwal.setOnClickListener {
            val pengajianFragment = PengajianFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, pengajianFragment)
            transaction.commit()
        }
        val jadwalpengajian = view.findViewById<TextView>(R.id.jadwalpengajian)
        jadwalpengajian.setOnClickListener {
            val pengajianFragment = PengajianFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, pengajianFragment)
            transaction.commit()
        }
        val gmbrmsjd1 = view.findViewById<ImageView>(R.id.gmbrmsjd1)
        gmbrmsjd1.setOnClickListener {
            val pengajianFragment = PengajianFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, pengajianFragment)
            transaction.commit()
        }
        val cardjdwlpengajian = view.findViewById<ImageView>(R.id.cardjdwlpengajian)
        cardjdwlpengajian.setOnClickListener {
            val pengajianFragment = PengajianFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fl, pengajianFragment)
            transaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val current = SimpleDateFormat("dd MMM yyyy")
        val tanggal = current.format(Date())
        tv_tanggalHome.text = tanggal

        s = SharedPref(requireActivity())
        val user = s.getUser()!!
        RetrofitClient.instance.getPt(user.id).enqueue(object: Callback<ArrayList<PengajianTerdekat>>{
            override fun onResponse(
                call: Call<ArrayList<PengajianTerdekat>>,
                response: Response<ArrayList<PengajianTerdekat>>
            ) {

            }
            override fun onFailure(call: Call<ArrayList<PengajianTerdekat>>, t: Throwable) {

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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
