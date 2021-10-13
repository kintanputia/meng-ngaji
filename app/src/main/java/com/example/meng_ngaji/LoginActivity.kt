package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvDaftar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvDaftar = findViewById(R.id.textViewDaftar)
        tvDaftar.setOnClickListener(this)
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