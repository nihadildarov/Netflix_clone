package com.example.myapplication.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btmNav.setBackgroundColor(resources.getColor(R.color.dark_gray))

        initBottomNav()
    }


    private fun initBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navHost = navController.navController
        binding.btmNav.setupWithNavController(navHost)
        btmNavVisibilityControl(navHost)
    }
    private fun btmNavVisibilityControl(navController: NavController) {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailsFragment -> binding.btmNav.visibility = View.GONE
                R.id.getStartedFragment -> binding.btmNav.visibility = View.GONE
                R.id.signUpFragment -> binding.btmNav.visibility = View.GONE
                R.id.signInFragment -> binding.btmNav.visibility = View.GONE
                R.id.createAccountFragment -> binding.btmNav.visibility = View.GONE
                R.id.accountsFragment -> binding.btmNav.visibility = View.GONE
                R.id.searchFragment -> binding.btmNav.visibility = View.GONE
                else -> binding.btmNav.visibility = View.VISIBLE
            }
        }
    }





}


