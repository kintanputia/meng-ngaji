package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.meng_ngaji.helper.*
import kotlinx.android.synthetic.main.activity_detail_profil.*
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

        s = SharedPref(this)
        setData()
    }

    fun setData() {
        val user = s.getUser()!!

        etNama.text = Editable.Factory.getInstance().newEditable(user.nama)
        etEmail.text = Editable.Factory.getInstance().newEditable(user.email)
        etNoHp.text = Editable.Factory.getInstance().newEditable(user.no_hp)
    }


}