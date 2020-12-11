package com.example.artapp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.artapp.databinding.ActivityMainBinding
import com.example.core.base.BaseActivity


class MainActivity : BaseActivity() {

    private var binder:ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment_nav_host)

        binder!!.mainNavigationBottom.itemIconTintList = null
        binder!!.mainNavigationBottom.setupWithNavController(navController)
    }


    override fun onDestroy() {
        binder = null
        super.onDestroy()
    }
}