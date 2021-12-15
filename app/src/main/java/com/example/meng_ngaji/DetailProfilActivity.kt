package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.meng_ngaji.helper.Profile
import com.example.meng_ngaji.helper.Retro
import com.example.meng_ngaji.helper.UserApi
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profil)

        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            title = " "

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        getDetailProfil()
    }

    fun getDetailProfil(){
        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.getDetailProfil().enqueue(object : Callback<List<Profile>> {
            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                val detail = response.body()
                for(n in detail!!){
                    etNama.text = Editable.Factory.getInstance().newEditable(n.nama)
                    etEmail.text = Editable.Factory.getInstance().newEditable(n.email)
                    etNoHp.text = Editable.Factory.getInstance().newEditable(n.no_hp)
                }
            }
        })
    }

}