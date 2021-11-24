package com.example.meng_ngaji

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_prayer.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.widget.ArrayAdapter as ArrayAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [PrayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrayerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listKota: MutableList<Kota>? = null
    private var mKotaAdapter: ArrayAdapter<Kota>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val v: View = inflater.inflate(R.layout.fragment_prayer, container, false)

        val spKota: Spinner = v.findViewById(R.id.kota)
        listKota = ArrayList()
        mKotaAdapter = ArrayAdapter(requireActivity().getApplicationContext(),
            android.R.layout.simple_spinner_item, listKota as ArrayList<Kota>)
        mKotaAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spKota.adapter = mKotaAdapter
        spKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val kota = mKotaAdapter!!.getItem(position)
                loadJadwal(kota?.id)
            }

        }

        loadKota()
        return v
    }

    private fun loadJadwal(id: Int?) {
        try {
            val idKota = id.toString()
            val current = SimpleDateFormat("yyyy-MM-dd")
            val tanggal = current.format(Date())
            val url = "https://api.banghasan.com/sholat/format/json/jadwal/kota/$idKota/tanggal/$tanggal"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        val jsonObj = JSONObject(result)
                        val objJadwal = jsonObj.getJSONObject("jadwal")
                        val obData = objJadwal.getJSONObject("data")

                        tv_tanggal.text = obData.getString("tanggal")
                        tv_subuh.text = obData.getString("subuh")
                        tv_dzuhur.text = obData.getString("dzuhur")
                        tv_ashar.text = obData.getString("ashar")
                        tv_maghrib.text = obData.getString("maghrib")
                        tv_isya.text = obData.getString("isya")

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun loadKota() {
        try {
            val url = "https://api.banghasan.com/sholat/format/json/kota"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        val jsonObj = JSONObject(result)
                        val jsonArray = jsonObj.getJSONArray("kota")
                        var kota: Kota?
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            kota = Kota()
                            kota.id = obj.getInt("id")
                            kota.nama = obj.getString("nama")
                            listKota!!.add(kota)
                        }
                        mKotaAdapter!!.notifyDataSetChanged()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}