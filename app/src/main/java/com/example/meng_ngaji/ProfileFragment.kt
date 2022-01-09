package com.example.meng_ngaji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.meng_ngaji.helper.ResponModel
import com.example.meng_ngaji.helper.*
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

    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        s = SharedPref(requireActivity())

        setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)
//        init(view)

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
                s.setStatusLogin(false)
                val intentAkun = Intent(context, MainActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }

    fun setData(){
        val user = s.getUser()!!

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.user(user.id).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }
            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    tvNama.text = respon.user.nama
                    tvEmail.text = respon.user.email
                }
            }
        })
    }

//    fun setData() {
//        val user = s.getUser()!!
//
//        tvNama.text = user.nama
//        tvEmail.text = user.email
//    }

//    private fun init(view: View) {
//
//    }

}