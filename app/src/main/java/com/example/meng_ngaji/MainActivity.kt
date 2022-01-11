package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.meng_ngaji.helper.ResponModel
import com.example.meng_ngaji.helper.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvDaftar : TextView
    private var statusLogin = false
    private lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDaftar = findViewById(R.id.textViewDaftar)
        tvDaftar.setOnClickListener(this)

        s = SharedPref(this)

        if (s.getStatusLogin() == true) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        else {
            buttonLogin.setOnClickListener {
                login()
            }
        }

    }

    fun login() {
        if (editEmail.text.isEmpty()) {
            editEmail.error = "Kolom Email tidak boleh kosong"
            editEmail.requestFocus()
            return
        } else if (editPassword.text.isEmpty()) {
            editPassword.error = "Kolom Password tidak boleh kosong"
            editPassword.requestFocus()
            return
        }

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(editEmail.text.toString(), editPassword.text.toString()).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error:" + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1) {
                    s.setStatusLogin(true)
                    s.setUser(respon.user)
//                    s.setString(s.nama, respon.user.nama)
//                    s.setString(s.no_hp, respon.user.no_hp)

                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Error:" + respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.textViewDaftar ->{
                val intentAkun = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }

}