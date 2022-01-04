package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.meng_ngaji.helper.*
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.activity_detail_profil.toolbar
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfilActivity : AppCompatActivity() {

    private lateinit var s: SharedPref

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

        btnEditUser.setOnClickListener {
            editUserApi()
        }

        s = SharedPref(this)
        setData()
    }

//    fun setData() {
//        val user = s.getUser()!!
//
//        etNama.text = Editable.Factory.getInstance().newEditable(user.nama)
//        etEmail.text = Editable.Factory.getInstance().newEditable(user.email)
//        etNoHp.text = Editable.Factory.getInstance().newEditable(user.no_hp)
//
//    }

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
                    etNama.text = Editable.Factory.getInstance().newEditable(respon.user.nama)
                    etEmail.text = Editable.Factory.getInstance().newEditable(respon.user.email)
                    etNoHp.text = Editable.Factory.getInstance().newEditable(respon.user.no_hp)
                }
            }
        })
    }

    fun editUserApi(){
        val user = s.getUser()!!

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.editUser(user.id, user.id, etNama.text.toString(), etEmail.text.toString(), etNoHp.text.toString()).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@DetailProfilActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
//                    s.setUser(respon.user)

                    Toast.makeText(this@DetailProfilActivity, "Perubahan Data User Berhasil ", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@DetailProfilActivity, "Error:" + respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}