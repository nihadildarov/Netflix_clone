package com.example.myapplication.fragments.GetStarted.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGetStartedBinding
import com.example.myapplication.fragments.GetStarted.adapter.ViewPagerAdapterGetStartedBG
import com.google.android.material.tabs.TabLayoutMediator

class GetStartedFragment : Fragment() {
    private lateinit var binding:FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater,container,false)
        setViewPagerAdapter()

        return binding.root
    }


    private fun setViewPagerAdapter(){
        val images = listOf<Int>(
            R.drawable.img  ,
            R.drawable.ic_netflix_logo,
            R.drawable.ic_netflix_short_logo,
            R.drawable.logo_netflix,
        )
        val titles = listOf<String>(
            "Unlimited movies, TV shows & more",
            "There's a plan for every fan",
            "Cancel online anytime",
            "Watch everywhere"
        )

        val descriptions = listOf<String>(
            "Watch anywhere. Cancel anytime",
            "Plans start at EUR 7.99.",
            "Join today, no reason to wait.",
            "Stream on your phone, tablet, laptop and TV."
        )



        val adapterBG = ViewPagerAdapterGetStartedBG(images,titles,descriptions)
        binding.getStartedViewPager.adapter = adapterBG

        TabLayoutMediator(binding.tabLayout,binding.getStartedViewPager){tab,position->


        }.attach()
    }

}