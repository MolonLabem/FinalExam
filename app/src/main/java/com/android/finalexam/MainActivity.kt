package com.android.finalexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.finalexam.view.CountriesListFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val countriesFragment = CountriesListFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainActivity, countriesFragment)
        fragmentTransaction.commit()
    }
}