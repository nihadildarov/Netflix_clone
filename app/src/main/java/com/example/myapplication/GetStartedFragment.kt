package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentGetStartedBinding

class GetStartedFragment : Fragment() {
    private lateinit var binding:FragmentGetStartedBinding
    private var titlesList = mutableListOf<String>()
    private var descsList = mutableListOf<String>()
    private var imgsList = mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater,container,false)

        postToList()
        binding.viewPagerGS.adapter=ViewPagerAdapter(titlesList,descsList,imgsList)
        binding.viewPagerGS.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicatorGS.set

        return binding.root
    }




    private fun addToList (title:String,desc:String,img:Int){
        titlesList.add(title)
        descsList.add(desc)
        imgsList.add(img)
    }



    private fun postToList(){
        for (i in 1..5){
            addToList("Title $i","Desc $i", R.drawable.logo_netflix)
        }
    }
}