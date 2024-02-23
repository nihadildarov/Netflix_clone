package com.example.myapplication.presentation.fragments.getstarted.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGetStartedBinding
import com.example.myapplication.presentation.fragments.getstarted.adapter.ViewPagerAdapterGetStartedBG
import com.example.myapplication.util.Constants.HELP
import com.example.myapplication.util.Constants.PRIVACY
import com.google.android.material.tabs.TabLayoutMediator

class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerAdapter()
        menuItemClick()
        btnGetStartedClick()
    }




    private fun openPage(url:String){

        val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(intent)
    }



    private fun menuItemClick() {


        binding.toolbar.overflowIcon?.setTint(resources.getColor(R.color.gray))
        binding.toolbar.setOnMenuItemClickListener {
            when (it.title) {
                "Privacy" -> {
                    openPage(PRIVACY)
                }
                "Login" -> findNavController().navigate(GetStartedFragmentDirections.actionGetStartedToSignIn(""))
                "HELP" -> {
                    openPage(HELP)
                }
                "FAQs" -> Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
            }
            true
        }

    }





    private fun setViewPagerAdapter() {
        val images = listOf(
            R.drawable.img,
            R.drawable.ic_netflix_logo,
            R.drawable.ic_netflix_short_logo,
            R.drawable.logo_netflix,
        )
        val titles = listOf(
            "Unlimited movies, TV shows & more",
            "There's a plan for every fan",
            "Cancel online anytime",
            "Watch everywhere"
        )

        val descriptions = listOf(
            "Watch anywhere. Cancel anytime",
            "Plans start at EUR 7.99.",
            "Join today, no reason to wait.",
            "Stream on your phone, tablet, laptop and TV."
        )


        val adapterBG = ViewPagerAdapterGetStartedBG(images, titles, descriptions)
        binding.getStartedViewPager.adapter = adapterBG

        TabLayoutMediator(binding.tabLayout, binding.getStartedViewPager) { tab, position ->
        }.attach()
    }



    private fun btnGetStartedClick(){
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(GetStartedFragmentDirections.actionGetStartedToSignInMain())
        }
    }




}