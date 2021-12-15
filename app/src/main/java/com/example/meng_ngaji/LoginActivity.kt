package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.meng_ngaji.helper.Retro
import com.example.meng_ngaji.helper.UserApi
import com.example.meng_ngaji.helper.UserRequest
import com.example.meng_ngaji.helper.UserResponse
import kotlinx.android.synthetic.main.activity_detailmasjid.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvDaftar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvDaftar = findViewById(R.id.textViewDaftar)
        tvDaftar.setOnClickListener(this)

        initAction()
    }

    fun initAction() {
        buttonLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        val request = UserRequest()
        request.email = editEmail.text.toString().trim()
        request.password = editPassword.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Gagal Login", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
//                Log.e("token", user!!.data?.token)
//                Log.e("email", user!!.data?.email)
                if(user != null){
                        val intentAkun = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intentAkun)
                }
                else{
                    Toast.makeText(this@LoginActivity, "Email anda Salah", Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.textViewDaftar ->{
                val intentAkun = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }
}

