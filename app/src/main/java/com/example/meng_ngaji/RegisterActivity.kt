package com.example.meng_ngaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView import kotlinx.android.synthetic.main.activity_detail_profil.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bDaftar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bDaftar = findViewById(R.id.buttonDaftar)
        bDaftar.setOnClickListener(this)

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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonDaftar ->{
                val intentAkun = Intent(this, LoginActivity::class.java)
                startActivity(intentAkun)
            }
        }
    }
}