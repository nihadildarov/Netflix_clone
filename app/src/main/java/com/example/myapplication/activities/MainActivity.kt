package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btmNav.background = null
        initBottomNav()
    }


    private fun initBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navHost = navController.navController
        binding.btmNav.setupWithNavController(navHost)
    }


}


