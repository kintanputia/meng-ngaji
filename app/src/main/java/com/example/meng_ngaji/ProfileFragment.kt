package com.example.meng_ngaji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.meng_ngaji.helper.Profile
import com.example.meng_ngaji.helper.Retro
import com.example.meng_ngaji.helper.UserApi
import com.example.meng_ngaji.helper.UserResponse
import kotlinx.android.synthetic.main.fragment_prayer.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var card1 : CardView
    private lateinit var card2 : CardView
    private lateinit var card3 : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getProfil()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)

        val card1: CardView = view.findViewById(R.id.cardDetailAkun)
        val card2: CardView = view.findViewById(R.id.cardUbahSandi)
        val card3: CardView = view.findViewById(R.id.cardKeluar)

        card1.setOnClickListener(this)
        card2.setOnClickListener(this)
        card3.setOnClickListener(this)
        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.cardDetailAkun -> {
                val intentAkun = Intent(context, DetailProfilActivity::class.java)
                startActivity(intentAkun)
            }
            R.id.cardUbahSandi -> {
                val intentAkun = Intent(context, ChangePasswordActivity::class.java)
                startActivity(intentAkun)
            }
            R.id.cardKeluar -> {
                val intentAkun = Intent(context, LoginActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }

    fun getProfil(){
        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.getProfil().enqueue(object : Callback<List<Profile>> {
            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                val profile = response.body()
                for(p in profile!!){
                    tvNama.text = p.nama
                    tvEmail.text = p.email
//                    Log.e("Hasil", p.nama)
                }
            }
        })
    }
}