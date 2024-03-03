package com.example.myapplication.presentation.activities

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Rect
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.NetworkManager
import dagger.hilt.android.AndroidEntryPoint
import javax.annotation.meta.When

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    class PiPReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            println("Clicked on PIP")
        }
    }

    private lateinit var binding: ActivityMainBinding

    //value to check if picture in picture mode supported
    private val isPipSupported by lazy {
        packageManager.hasSystemFeature(
            PackageManager.FEATURE_PICTURE_IN_PICTURE
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNav()
        binding.btmNav.setBackgroundColor(resources.getColor(R.color.dark_gray))
        btmNavVisibilityControl()
        setOrientation()
        networkStatus()
    }


    private fun setNavHost(): NavController {
        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navController.navController
    }


    private fun updatedPipParams(): PictureInPictureParams? {

        val navHost = setNavHost()
        var isFullScreen = false
        navHost.addOnDestinationChangedListener { _, destination, _ ->
            isFullScreen = when(destination.id){
                R.id.fullScreenFragment -> true
                else -> false
            }
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isFullScreen ) {
            PictureInPictureParams.Builder()
                .setSourceRectHint( //Smooth animation to the pip window
                    Rect()
                )
                .setAspectRatio(Rational(16, 9))
                .setActions(
                    listOf(
                        RemoteAction(
                            Icon.createWithResource(applicationContext, R.drawable.ic_games),
                            "PIP WINDOW",
                            "PIP WINDOW",
                            PendingIntent.getBroadcast(
                                applicationContext,
                                0,
                                Intent(applicationContext, PiPReceiver::class.java),
                                PendingIntent.FLAG_IMMUTABLE
                            )
                        )
                    )
                )
                .build()
        } else null
    }

    //PIP mode detection
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()

        if (!isPipSupported) {

            return
        } else {
            updatedPipParams()?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureMode(it)
                }
            }
        }
    }


    private fun initNav() {
        val navHost = setNavHost()
        binding.btmNav.setupWithNavController(navHost)
    }





    private fun btmNavVisibilityControl() {
        val navHost = setNavHost()

        navHost.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fullScreenFragment -> binding.btmNav.visibility = View.GONE
                R.id.searchFragment -> binding.btmNav.visibility = View.GONE
                R.id.verifyEmailFragment -> binding.btmNav.visibility = View.GONE
                R.id.accountsFragment -> binding.btmNav.visibility = View.GONE
                R.id.movieDetailsFragment -> binding.btmNav.visibility = View.GONE
                R.id.createAccountFragment -> binding.btmNav.visibility = View.GONE
                R.id.getStartedFragment -> binding.btmNav.visibility = View.GONE
                R.id.downloadsFragment -> binding.btmNav.visibility = View.VISIBLE
                R.id.gamesFragment -> binding.btmNav.visibility = View.GONE
                R.id.homeFragment -> binding.btmNav.visibility = View.VISIBLE
                R.id.newHotFragment -> binding.btmNav.visibility = View.VISIBLE
                R.id.pinDialogFragment -> binding.btmNav.visibility = View.GONE
                R.id.finishSignUpFragment -> binding.btmNav.visibility = View.GONE

            }
        }



    }


    private fun setOrientation() {
        val navHost = setNavHost()

        navHost.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.fullScreenFragment -> this.requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                else -> this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }

        }

    }


    private fun networkStatus() {

        val networkManager = NetworkManager(this)
        networkManager.observe(this) {
            if (!it) {
                Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show()
            }
        }


    }


}


