package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.example.meng_ngaji.helper.ResponModel
import com.example.meng_ngaji.helper.Retro
import com.example.meng_ngaji.helper.SharedPref
import com.example.meng_ngaji.helper.UserApi
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.activity_detail_profil.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var s: SharedPref
    lateinit var currrentPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            title = " "

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        btnUbahPassword.setOnClickListener {
            editPasswordApi()
        }

        s = SharedPref(this)
        setData()
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
                    currrentPassword = respon.user.password
                }
            }
        })
    }

    fun editPasswordApi(){
        val user = s.getUser()!!

        if(currrentPassword == etCurrentPassword.text.toString()) {
            if(etUbahPassword.text.toString() == etKonfirmPassword.text.toString()) {
                val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
                retro.editPassword(user.id, etUbahPassword.text.toString()).enqueue(object :
                    Callback<ResponModel> {
                    override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                        Toast.makeText(this@ChangePasswordActivity,
                            "Error:" + t.message,
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                        val respon = response.body()!!
                        if (respon.success == 1) {
                            Toast.makeText(this@ChangePasswordActivity, "Perubahan Password Berhasil ", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@ChangePasswordActivity, "Perubahan Password Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            else{
                Toast.makeText(this@ChangePasswordActivity, "Password baru tidak cocok", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this@ChangePasswordActivity, "Password saat ini masih salah", Toast.LENGTH_SHORT).show()
        }
    }
}