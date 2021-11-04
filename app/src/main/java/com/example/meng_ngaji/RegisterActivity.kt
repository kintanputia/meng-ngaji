package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bDaftar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bDaftar = findViewById(R.id.buttonDaftar)
        bDaftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonDaftar ->{
                val intentAkun = Intent(this @RegisterActivity, LoginActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }
}