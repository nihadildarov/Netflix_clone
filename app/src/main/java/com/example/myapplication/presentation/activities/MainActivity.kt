package com.example.myapplication.presentation.activities

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.NetworkManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var observer: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btmNav.setBackgroundColor(resources.getColor(R.color.dark_gray))

        networkStatus()
        initNav()
    }






    private fun initNav(){

        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navHost = navController.navController
        binding.btmNav.setupWithNavController(navHost)

        btmNavVisibilityControl(navHost)
        setOrientation(navHost)


    }




    private fun btmNavVisibilityControl(navController: NavController) {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailsFragment -> binding.btmNav.visibility = View.GONE
                R.id.verifyEmailFragment -> binding.btmNav.visibility = View.GONE
                R.id.getStartedFragment -> binding.btmNav.visibility = View.GONE
                R.id.signUpFragment -> binding.btmNav.visibility = View.GONE
                R.id.signInFragment -> binding.btmNav.visibility = View.GONE
                R.id.createAccountFragment -> binding.btmNav.visibility = View.GONE
                R.id.accountsFragment -> binding.btmNav.visibility = View.GONE
                R.id.searchFragment -> binding.btmNav.visibility = View.GONE
                R.id.fullScreenFragment -> binding.btmNav.visibility = View.GONE
                else -> binding.btmNav.visibility = View.VISIBLE
            }
        }
    }


    private fun setOrientation(navController: NavController){
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){

                R.id.fullScreenFragment -> this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else -> this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }

        }

    }


    private fun networkStatus(){

        val networkManager = NetworkManager(this)
        networkManager.observe(this){
            if(!it){
                Toast.makeText(this,"No internet connection!",Toast.LENGTH_LONG).show()
            }
        }


    }




}


