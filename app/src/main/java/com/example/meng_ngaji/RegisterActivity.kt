package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.meng_ngaji.helper.ResponModel
import com.example.meng_ngaji.helper.Retro
import com.example.meng_ngaji.helper.SharedPref
import com.example.meng_ngaji.helper.UserApi
import kotlinx.android.synthetic.main.activity_detail_profil.*
import kotlinx.android.synthetic.main.activity_detail_profil.toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bDaftar : Button
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bDaftar = findViewById(R.id.buttonDaftar)
        bDaftar.setOnClickListener(this)

        s = SharedPref(this)

        buttonDaftar.setOnClickListener {
            register()
        }

        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            title = " "

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    fun register() {
        if (etNamaLengkap.text.isEmpty()) {
            etNamaLengkap.error = "Kolom Nama tidak boleh kosong"
            etNamaLengkap.requestFocus()
            return
        } else if (etEmailAddress.text.isEmpty()) {
            etEmailAddress.error = "Kolom Email tidak boleh kosong"
            etEmailAddress.requestFocus()
            return
        } else if (etNo_Hp.text.isEmpty()) {
            etNo_Hp.error = "Kolom Nomor Telepon tidak boleh kosong"
            etNo_Hp.requestFocus()
            return
        } else if (etPassword.text.isEmpty()) {
            etPassword.error = "Kolom Password tidak boleh kosong"
            etPassword.requestFocus()
            return
        }

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.register(etNamaLengkap.text.toString(), etEmailAddress.text.toString(), etNo_Hp.text.toString(), etPassword.text.toString()).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Selamat datang " + respon.user.nama, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity, "Error:" + respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonDaftar ->{
                val intentAkun = Intent(this, MainActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }
}