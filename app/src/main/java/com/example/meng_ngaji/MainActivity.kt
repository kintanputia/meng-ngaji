package com.example.meng_ngaji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigationListener)
        var fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.fl, HomeFragment())
        fragment.commit()
    }

    private val onBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment : Fragment = HomeFragment()

        when(item.itemId) {
            R.id.user -> {
                selectedFragment = ProfileFragment()
            }
            R.id.home -> {
                selectedFragment = HomeFragment()
            }
            R.id.masjid -> {
                selectedFragment = LokasiPenggunaFragment()
            }
            R.id.pengajian -> {
                selectedFragment = PengajianFragment()
            }
            R.id.prayer -> {
                selectedFragment = PrayerFragment()
            }
        }

        var fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fl, selectedFragment)
        fragment.commit()
        true

    }
}